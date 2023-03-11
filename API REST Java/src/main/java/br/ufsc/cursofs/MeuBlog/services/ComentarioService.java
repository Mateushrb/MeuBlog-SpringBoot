package br.ufsc.cursofs.MeuBlog.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsc.cursofs.MeuBlog.entities.Comentario;
import br.ufsc.cursofs.MeuBlog.repositories.ComentarioRepository;

@Service
public class ComentarioService {
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public Comentario update(Long id, Comentario comentario) {
		Comentario comentarioEntity = comentarioRepository.getReferenceById(id);
		
		comentarioEntity.setNome(comentario.getNome());
		comentarioEntity.setText(comentario.getText());
		
		return comentarioRepository.save(comentarioEntity);
	}
	
	public void deleteById(Long id) {
		comentarioRepository.deleteById(id);
	}
	
	public Comentario save(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}
	
	public List<Comentario> findAll() {
		return comentarioRepository.findAll();
	}
	
	public Comentario findById(Long id) {
		try {
			return comentarioRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Pedido id: " + id);
		}
	}
}
