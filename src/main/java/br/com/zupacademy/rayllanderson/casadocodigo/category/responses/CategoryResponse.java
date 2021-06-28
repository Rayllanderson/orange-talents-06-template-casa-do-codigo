package br.com.zupacademy.rayllanderson.casadocodigo.category.responses;

import br.com.zupacademy.rayllanderson.casadocodigo.category.model.Category;

public class CategoryResponse {
    private final String name;

    public CategoryResponse(Category category) {
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }
}
