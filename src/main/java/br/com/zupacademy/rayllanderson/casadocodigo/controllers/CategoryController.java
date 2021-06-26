package br.com.zupacademy.rayllanderson.casadocodigo.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.dtos.request.CategoryPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.entities.Category;
import br.com.zupacademy.rayllanderson.casadocodigo.repositories.CategoryRepository;
import br.com.zupacademy.rayllanderson.casadocodigo.validators.DuplicateCategoryNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final DuplicateCategoryNameValidator duplicateCategoryNameValidator;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, DuplicateCategoryNameValidator duplicateCategoryNameValidator) {
        this.categoryRepository = categoryRepository;
        this.duplicateCategoryNameValidator = duplicateCategoryNameValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(duplicateCategoryNameValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> register(@RequestBody @Valid CategoryPostRequest request){
        Category categoryToBeSaved = request.toCategory();
        categoryRepository.save(categoryToBeSaved);
        return ResponseEntity.ok().build();
    }
}
