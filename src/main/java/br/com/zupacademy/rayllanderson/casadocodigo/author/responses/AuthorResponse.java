package br.com.zupacademy.rayllanderson.casadocodigo.author.responses;


import br.com.zupacademy.rayllanderson.casadocodigo.author.model.Author;

public class AuthorResponse {

    private final String name;
    private final String description;

    public AuthorResponse(Author author) {
        this.name = author.getName();
        this.description = author.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
