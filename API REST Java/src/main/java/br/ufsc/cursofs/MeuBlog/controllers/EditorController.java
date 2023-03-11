package br.ufsc.cursofs.MeuBlog.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufsc.cursofs.MeuBlog.entities.Editor;
import br.ufsc.cursofs.MeuBlog.entities.Postagem;
import br.ufsc.cursofs.MeuBlog.services.EditorService;
import br.ufsc.cursofs.MeuBlog.services.PostagemService;

@RestController
public class EditorController {
	
	@Autowired
	private EditorService editorService;
	
	@Autowired
	private PostagemService postagemService;
	
	@GetMapping(value = "/editores/{id}/getPostagens")
	public ResponseEntity<List<Postagem>> getPostagens(@PathVariable Long id) {
		List<Postagem> postagens = postagemService.findAll();
		List<Postagem> postagensEditor = new ArrayList<>();
		for (Postagem p : postagens) {
			if (p.getEditor().getId() == id) {
				postagensEditor.add(p);
			}
		}
		return ResponseEntity.ok().body(postagensEditor);
	}
	
	@PutMapping(value = "/editores/{id}")
	public ResponseEntity<Editor> update(@PathVariable Long id, @RequestBody Editor editor) {
		Editor editorAtualizado = editorService.update(id, editor);
		return ResponseEntity.ok().body(editorAtualizado);
	}
	
	@DeleteMapping(value = "/editores/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		editorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/editores")
	public ResponseEntity<Editor> save(@RequestBody Editor editor) {
		Editor editorSaved = editorService.save(editor);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/editores/{id}").buildAndExpand(editorSaved.getId()).toUri();
		return ResponseEntity.created(uri).body(editorSaved);
	}
	
	@GetMapping(value = "/editores")
	public ResponseEntity<List<Editor>> findAll() {
		List<Editor> editores = editorService.findAll();
		return ResponseEntity.ok().body(editores);
	}
	
	@GetMapping(value = "/editores/{id}")
	public ResponseEntity<Editor> findById(@PathVariable Long id) {
		Editor e = editorService.findById(id);
		return ResponseEntity.ok().body(e);
	}
}
