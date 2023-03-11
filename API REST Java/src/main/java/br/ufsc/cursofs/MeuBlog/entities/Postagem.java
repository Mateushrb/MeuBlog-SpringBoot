package br.ufsc.cursofs.MeuBlog.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Postagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Instant data;
	private String titulo;
	@Column(length = 10000)
	private String texto;
	
	@ManyToOne
	@JoinColumn(name = "editor_id")
	private Editor editor;
	
	@OneToMany(mappedBy = "postagem")
	private List<Comentario> comentarios = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "postagems_assuntos", joinColumns = @JoinColumn(name = "postagem_id"), inverseJoinColumns = @JoinColumn(name = "assunto_id"))
	private List<Assunto> assuntos = new ArrayList<>();
	
	public Postagem() {}
	
	public Postagem(Long id, Instant data, String titulo, String texto, Editor editor) {
		this.id = id;
		this.data = data;
		this.titulo = titulo;
		this.texto = texto;
		this.editor = editor;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Instant getData() {
		return data;
	}
	public void setData(Instant data) {
		this.data = data;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Editor getEditor() {
		return editor;
	}
	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void addComentario(Comentario comentario) {
		comentarios.add(comentario);
	}
	public void removeComentario(Comentario comentario) {
		comentarios.remove(comentario);
	}
	
	public List<Assunto> getAssuntos() {
		return assuntos;
	}
	public void addAssunto(Assunto assunto) {
		assuntos.add(assunto);
	}
	public void removeAssunto(Assunto assunto) {
		assuntos.remove(assunto);
	}
	
	public Double getTotalPalavras() {
		String[] palavras = texto.split(" ");
		Double totalPalavras = (double) palavras.length;
		return totalPalavras;
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
		Postagem other = (Postagem) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
