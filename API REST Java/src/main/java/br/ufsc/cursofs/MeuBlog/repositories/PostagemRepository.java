package br.ufsc.cursofs.MeuBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsc.cursofs.MeuBlog.entities.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
