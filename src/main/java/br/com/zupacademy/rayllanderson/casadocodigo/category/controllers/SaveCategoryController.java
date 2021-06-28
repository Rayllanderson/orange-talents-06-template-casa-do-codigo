package br.com.zupacademy.rayllanderson.casadocodigo.category.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.category.requests.CategoryPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.category.model.Category;
import br.com.zupacademy.rayllanderson.casadocodigo.category.repository.CategoryRepository;
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
public class SaveCategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public SaveCategoryController(CategoryRepository categoryRepository) {
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
