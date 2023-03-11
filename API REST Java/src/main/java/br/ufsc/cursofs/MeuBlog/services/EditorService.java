package br.ufsc.cursofs.MeuBlog.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufsc.cursofs.MeuBlog.entities.Editor;
import br.ufsc.cursofs.MeuBlog.repositories.EditorRepository;

@Service
public class EditorService {
	
	@Autowired
	private EditorRepository editorRepository;
	
	public Editor update(Long id, Editor editor) {
		Editor editorEntity = editorRepository.getReferenceById(id);
		
		editorEntity.setNome(editor.getNome());
		editorEntity.setSenha(editor.getSenha());
		
		return editorRepository.save(editorEntity);
	}
	
	public void deleteById(Long id) {
		editorRepository.deleteById(id);
	}
	
	public Editor save(Editor editor) {
		return editorRepository.save(editor);
	}
	
	public List<Editor> findAll() {
		return editorRepository.findAll();
	}
	
	public Editor findById(Long id) {
		try {
			return editorRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Pedido id: " + id);
		}
	}
	
}
