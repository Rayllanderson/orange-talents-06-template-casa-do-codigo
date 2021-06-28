package br.com.zupacademy.rayllanderson.casadocodigo.dtos.request;

import br.com.zupacademy.rayllanderson.casadocodigo.entities.Category;
import br.com.zupacademy.rayllanderson.casadocodigo.validators.annotations.UniqueValue;
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
