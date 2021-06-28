package br.com.zupacademy.rayllanderson.casadocodigo.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.dtos.request.BookPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.entities.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    @PersistenceContext
    private final EntityManager manager;

    public BookController(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> register (@RequestBody @Valid BookPostRequest request){
        Book bookToBeSaved = request.toBook(manager);
        manager.persist(bookToBeSaved);
        return ResponseEntity.ok().build();
    }
}
