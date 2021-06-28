package br.com.zupacademy.rayllanderson.casadocodigo.author.repository;

import br.com.zupacademy.rayllanderson.casadocodigo.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);
}
