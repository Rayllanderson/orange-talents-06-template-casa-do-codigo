package br.com.zupacademy.rayllanderson.casadocodigo.dtos.request;

import br.com.zupacademy.rayllanderson.casadocodigo.entities.Category;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CategoryPostRequest {

    @NotBlank
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
