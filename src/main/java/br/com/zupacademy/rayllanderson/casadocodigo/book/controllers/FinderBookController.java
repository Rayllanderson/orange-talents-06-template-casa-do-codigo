package br.com.zupacademy.rayllanderson.casadocodigo.book.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.book.model.Book;
import br.com.zupacademy.rayllanderson.casadocodigo.book.repository.BookRepository;
import br.com.zupacademy.rayllanderson.casadocodigo.book.responses.BookDetailsResponse;
import br.com.zupacademy.rayllanderson.casadocodigo.book.responses.BookListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class FinderBookController {

    private final BookRepository bookRepository;

    @Autowired
    public FinderBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<List<BookListResponse>> findAll(){
        return ResponseEntity.ok(BookListResponse.fromBookList(bookRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsResponse> findById(@PathVariable Long id) {
        Book bookToBeSearched = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(new BookDetailsResponse(bookToBeSearched));
    }
}
