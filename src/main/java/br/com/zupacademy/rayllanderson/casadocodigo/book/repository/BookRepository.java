package br.com.zupacademy.rayllanderson.casadocodigo.book.repository;

import br.com.zupacademy.rayllanderson.casadocodigo.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
