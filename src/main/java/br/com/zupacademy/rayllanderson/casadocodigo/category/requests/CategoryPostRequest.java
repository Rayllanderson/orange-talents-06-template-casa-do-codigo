package br.com.zupacademy.rayllanderson.casadocodigo.category.requests;

import br.com.zupacademy.rayllanderson.casadocodigo.category.model.Category;
import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CategoryPostRequest {

    @NotBlank
    @UniqueValue(domainClass = Category.class, field = "name")
    private String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoryPostRequest(@NotBlank String name) {
        this.name = name;
    }

    public Category toCategory(){
        return new Category(name);
    }

    public String getName() {
        return name;
    }
}
