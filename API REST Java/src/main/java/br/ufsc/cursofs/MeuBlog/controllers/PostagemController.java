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

import br.ufsc.cursofs.MeuBlog.entities.Postagem;
import br.ufsc.cursofs.MeuBlog.services.PostagemService;

@RestController
public class PostagemController {
	
	@Autowired
	private PostagemService postagemService;
	
	@PutMapping(value = "/postagens/{id_postagem}/removeAssunto/{id_assunto}")
	public ResponseEntity<Postagem> removeAssunto(@PathVariable Long id_postagem, @PathVariable Long id_assunto) {
		Postagem postagem = postagemService.removeAssunto(id_postagem, id_assunto);
		return ResponseEntity.ok().body(postagem);
	}
	
	@PutMapping(value = "/postagens/{id_postagem}/addAssunto/{id_assunto}")
	public ResponseEntity<Postagem> addAssunto(@PathVariable Long id_postagem, @PathVariable Long id_assunto) {
		Postagem postagem = postagemService.addAssunto(id_postagem, id_assunto);
		return ResponseEntity.ok().body(postagem);
	}
	
	@PutMapping(value = "/postagens/{id_postagem}/removeComentario/{id_comentario}")
	public ResponseEntity<Postagem> removeComentario(@PathVariable Long id_postagem, @PathVariable Long id_comentario) {
		Postagem postagem = postagemService.removeComentario(id_postagem, id_comentario);
		return ResponseEntity.ok().body(postagem);
	}
	
	@PutMapping(value = "/postagens/{id_postagem}/addComentario/{id_comentario}")
	public ResponseEntity<Postagem> addComentario(@PathVariable Long id_postagem, @PathVariable Long id_comentario) {
		Postagem postagem = postagemService.addComentario(id_postagem, id_comentario);
		return ResponseEntity.ok().body(postagem);
	}
	
	@PutMapping(value = "/postagens/{id}")
	public ResponseEntity<Postagem> update(@PathVariable Long id, @RequestBody Postagem postagem) {
		Postagem postagemAtualizado = postagemService.update(id, postagem);
		return ResponseEntity.ok().body(postagemAtualizado);
	}
	
	@DeleteMapping(value = "/postagens/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		postagemService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/postagens")
	public ResponseEntity<Postagem> save(@RequestBody Postagem postagem) {
		Postagem postagemSaved = postagemService.save(postagem);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/postagemes/{id}").buildAndExpand(postagemSaved.getId()).toUri();
		return ResponseEntity.created(uri).body(postagemSaved);
	}
	
	@GetMapping(value = "/postagens")
	public ResponseEntity<List<Postagem>> findAll() {
		List<Postagem> postagemes = postagemService.findAll();
		return ResponseEntity.ok().body(postagemes);
	}
	
	@GetMapping(value = "/postagens/{id}")
	public ResponseEntity<Postagem> findById(@PathVariable Long id) {
		Postagem p = postagemService.findById(id);
		return ResponseEntity.ok().body(p);
	}
}
