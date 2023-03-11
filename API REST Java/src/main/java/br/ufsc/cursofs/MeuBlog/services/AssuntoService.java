package br.ufsc.cursofs.MeuBlog.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsc.cursofs.MeuBlog.entities.Assunto;
import br.ufsc.cursofs.MeuBlog.repositories.AssuntoRepository;

@Service
public class AssuntoService {
	
	@Autowired
	private AssuntoRepository assuntoRepository;
	
	public Assunto update(Long id, Assunto assunto) {
		Assunto assuntoEntity = assuntoRepository.getReferenceById(id);
		
		assuntoEntity.setDescricao(assunto.getDescricao());
		
		return assuntoRepository.save(assuntoEntity);
	}
	
	public void deleteById(Long id) {
		assuntoRepository.deleteById(id);
	}
	
	public Assunto save(Assunto assunto) {
		return assuntoRepository.save(assunto);
	}
	
	public List<Assunto> findAll() {
		return assuntoRepository.findAll();
	}
	
	public Assunto findById(Long id) {
		try {
			return assuntoRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Pedido id: " + id);
		}
	}
}
