package br.com.zupacademy.rayllanderson.casadocodigo.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.dtos.request.AuthorPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.entities.Author;
import br.com.zupacademy.rayllanderson.casadocodigo.repositories.AuthorRepository;
import br.com.zupacademy.rayllanderson.casadocodigo.validators.DuplicateEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final DuplicateEmailValidator duplicateEmailValidator;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, DuplicateEmailValidator duplicateEmailValidator) {
        this.authorRepository = authorRepository;
        this.duplicateEmailValidator = duplicateEmailValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(duplicateEmailValidator);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> registerAuthor(@RequestBody @Valid AuthorPostRequest authorRequest){
        Author authorToBeSaved = authorRequest.toAuthor();
        authorRepository.save(authorToBeSaved);
        return ResponseEntity.ok().build();
    }
}
