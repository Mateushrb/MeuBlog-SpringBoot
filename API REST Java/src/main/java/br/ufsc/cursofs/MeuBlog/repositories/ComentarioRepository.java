package br.ufsc.cursofs.MeuBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsc.cursofs.MeuBlog.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
