package br.ufsc.cursofs.MeuBlog.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Editor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String senha;
	
	@JsonIgnore
	@OneToMany(mappedBy = "editor")
	private List<Postagem> postagens = new ArrayList<>();
	
	public Editor() {}
	
	public Editor(Long id, String nome, String senha) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}
	public void addPostagem(Postagem postagen) {
		this.postagens.add(postagen);
	}
	
	public Double getTotalPostagens() {
		return (double) postagens.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editor other = (Editor) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
