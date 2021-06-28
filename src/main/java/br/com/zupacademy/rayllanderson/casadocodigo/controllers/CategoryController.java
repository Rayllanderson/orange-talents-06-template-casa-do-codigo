package br.com.zupacademy.rayllanderson.casadocodigo.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.dtos.request.CategoryPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.entities.Category;
import br.com.zupacademy.rayllanderson.casadocodigo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> register(@RequestBody @Valid CategoryPostRequest request){
        Category categoryToBeSaved = request.toCategory();
        categoryRepository.save(categoryToBeSaved);
        return ResponseEntity.ok().build();
    }
}
