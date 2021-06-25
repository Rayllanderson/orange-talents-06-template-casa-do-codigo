package br.com.zupacademy.rayllanderson.casadocodigo.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.dtos.request.AuthorRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.entities.Author;
import br.com.zupacademy.rayllanderson.casadocodigo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> registerAuthor(@RequestBody @Valid AuthorRequest authorRequest){
        Author authorToBeSaved = authorRequest.toAuthor();
        authorRepository.save(authorToBeSaved);
        return ResponseEntity.ok().build();
    }
}
