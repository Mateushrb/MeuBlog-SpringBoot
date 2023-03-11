package br.ufsc.cursofs.MeuBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsc.cursofs.MeuBlog.entities.Assunto;

public interface AssuntoRepository extends JpaRepository<Assunto, Long> {

}
