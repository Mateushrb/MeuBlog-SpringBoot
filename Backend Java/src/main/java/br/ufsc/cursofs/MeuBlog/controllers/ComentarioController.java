package br.ufsc.cursofs.MeuBlog.controllers;

import java.net.URI;
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

import br.ufsc.cursofs.MeuBlog.entities.Comentario;
import br.ufsc.cursofs.MeuBlog.entities.Postagem;
import br.ufsc.cursofs.MeuBlog.services.ComentarioService;

@RestController
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;

	
	@GetMapping(value = "/comentarios/{id}/getPostagem")
	public ResponseEntity<Postagem> getPostagem(@PathVariable Long id) {
		Comentario comentario = comentarioService.findById(id);

		return ResponseEntity.ok().body(comentario.getPostagem());
	}
	
	@PutMapping(value = "/comentarios/{id}")
	public ResponseEntity<Comentario> update(@PathVariable Long id, @RequestBody Comentario comentario) {
		Comentario comentarioAtualizado = comentarioService.update(id, comentario);
		return ResponseEntity.ok().body(comentarioAtualizado);
	}
	
	@DeleteMapping(value = "/comentarios/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		comentarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/comentarios")
	public ResponseEntity<Comentario> save(@RequestBody Comentario comentario) {
		Comentario comentarioSaved = comentarioService.save(comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/comentarios/{id}").buildAndExpand(comentarioSaved.getId()).toUri();
		return ResponseEntity.created(uri).body(comentarioSaved);
	}
	
	@GetMapping(value = "/comentarios")
	public ResponseEntity<List<Comentario>> findAll() {
		List<Comentario> comentarios = comentarioService.findAll();
		return ResponseEntity.ok().body(comentarios);
	}
	
	@GetMapping(value = "/comentarios/{id}")
	public ResponseEntity<Comentario> findById(@PathVariable Long id) {
		Comentario e = comentarioService.findById(id);
		return ResponseEntity.ok().body(e);
	}
}
