package br.com.zupacademy.rayllanderson.casadocodigo.dtos.request;

import br.com.zupacademy.rayllanderson.casadocodigo.entities.Author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 400)
    private String description;

    public Author toAuthor() {
        return new Author(name, email, description);
    }

    public AuthorRequest(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(max = 400)String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
