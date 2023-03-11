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

import br.ufsc.cursofs.MeuBlog.entities.Assunto;
import br.ufsc.cursofs.MeuBlog.entities.Postagem;
import br.ufsc.cursofs.MeuBlog.services.AssuntoService;

@RestController
public class AssuntoController {
	@Autowired
	private AssuntoService assuntoService;

	
	@GetMapping(value = "/assuntos/{id}/getPostagens")
	public ResponseEntity<List<Postagem>> getPostagem(@PathVariable Long id) {
		Assunto assunto = assuntoService.findById(id);
		List<Postagem> assuntoPostagens = new ArrayList<>();
		for (Postagem p : assunto.getPostagens()) {
			assuntoPostagens.add(p);
		}
		return ResponseEntity.ok().body(assuntoPostagens);
	}
	
	@PutMapping(value = "/assuntos/{id}")
	public ResponseEntity<Assunto> update(@PathVariable Long id, @RequestBody Assunto assunto) {
		Assunto assuntoAtualizado = assuntoService.update(id, assunto);
		return ResponseEntity.ok().body(assuntoAtualizado);
	}
	
	@DeleteMapping(value = "/assuntos/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		assuntoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/assuntos")
	public ResponseEntity<Assunto> save(@RequestBody Assunto assunto) {
		Assunto assuntoSaved = assuntoService.save(assunto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/assuntos/{id}").buildAndExpand(assuntoSaved.getId()).toUri();
		return ResponseEntity.created(uri).body(assuntoSaved);
	}
	
	@GetMapping(value = "/assuntos")
	public ResponseEntity<List<Assunto>> findAll() {
		List<Assunto> assuntos = assuntoService.findAll();
		return ResponseEntity.ok().body(assuntos);
	}
	
	@GetMapping(value = "/assuntos/{id}")
	public ResponseEntity<Assunto> findById(@PathVariable Long id) {
		Assunto e = assuntoService.findById(id);
		return ResponseEntity.ok().body(e);
	}
}
