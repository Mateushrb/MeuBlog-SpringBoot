package br.ufsc.cursofs.MeuBlog.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsc.cursofs.MeuBlog.entities.Assunto;
import br.ufsc.cursofs.MeuBlog.entities.Comentario;
import br.ufsc.cursofs.MeuBlog.entities.Postagem;
import br.ufsc.cursofs.MeuBlog.repositories.AssuntoRepository;
import br.ufsc.cursofs.MeuBlog.repositories.ComentarioRepository;
import br.ufsc.cursofs.MeuBlog.repositories.PostagemRepository;

@Service
public class PostagemService {
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private AssuntoRepository assuntoRepository;
	
	public Postagem removeAssunto(Long id_postagem, Long id_assunto) {
		Postagem postagem = postagemRepository.findById(id_postagem).get();
		Assunto assunto = assuntoRepository.findById(id_assunto).get();
		
		assunto.removePostagem(postagem);;
		assuntoRepository.save(assunto);
		
		postagem.removeAssunto(assunto);
		update(id_postagem, postagem);
		
		return postagem;
	}
	
	public Postagem addAssunto(Long id_postagem, Long id_assunto) {
		Postagem postagem = postagemRepository.findById(id_postagem).get();
		Assunto assunto = assuntoRepository.findById(id_assunto).get();
		
		assunto.addPostagem(postagem);
		assuntoRepository.save(assunto);
		
		postagem.addAssunto(assunto);
		update(id_postagem, postagem);
		
		return postagem;
	}
	
	public Postagem removeComentario(Long id_postagem, Long id_comentario) {
		Postagem postagem = postagemRepository.findById(id_postagem).get();
		Comentario comentario = comentarioRepository.findById(id_comentario).get();
		
		comentario.setPostagem(null);
		comentarioRepository.save(comentario);
		
		postagem.removeComentario(comentario);
		update(id_postagem, postagem);
		
		return postagem;
	}
	
	public Postagem addComentario(Long id_postagem, Long id_comentario) {
		Postagem postagem = postagemRepository.findById(id_postagem).get();
		Comentario comentario = comentarioRepository.findById(id_comentario).get();
		
		comentario.setPostagem(postagem);
		comentarioRepository.save(comentario);
		
		postagem.addComentario(comentario);
		update(id_postagem, postagem);
		
		return postagem;
	}
	
	public Postagem update(Long id, Postagem postagem) {
		Postagem postagemEntity = postagemRepository.getReferenceById(id);
		
		postagemEntity.setData(postagem.getData());
		postagemEntity.setTitulo(postagem.getTitulo());
		postagemEntity.setTexto(postagem.getTexto());
		postagemEntity.setEditor(postagem.getEditor());
		
		return postagemRepository.save(postagemEntity);
	}
	
	public void deleteById(Long id) {
		postagemRepository.deleteById(id);
	}
	
	public Postagem save(Postagem postagem) {
		return postagemRepository.save(postagem);
	}
	
	public List<Postagem> findAll() {
		return postagemRepository.findAll();
	}
	
	public Postagem findById(Long id) {
		try {
			return postagemRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Pedido id: " + id);
		}
	}
}
