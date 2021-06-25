package br.com.zupacademy.rayllanderson.casadocodigo.repositories;

import br.com.zupacademy.rayllanderson.casadocodigo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
