package br.ufsc.cursofs.MeuBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsc.cursofs.MeuBlog.entities.Editor;

public interface EditorRepository extends JpaRepository<Editor, Long> {

}
