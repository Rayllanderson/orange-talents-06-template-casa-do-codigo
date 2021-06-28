package br.com.zupacademy.rayllanderson.casadocodigo.author.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.author.requests.AuthorPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.author.model.Author;
import br.com.zupacademy.rayllanderson.casadocodigo.author.repository.AuthorRepository;
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
public class SaveAuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public SaveAuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> registerAuthor(@RequestBody @Valid AuthorPostRequest authorRequest){
        Author authorToBeSaved = authorRequest.toAuthor();
        authorRepository.save(authorToBeSaved);
        return ResponseEntity.ok().build();
    }
}
