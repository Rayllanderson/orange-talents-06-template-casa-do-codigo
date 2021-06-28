package br.com.zupacademy.rayllanderson.casadocodigo.author.requests;

import br.com.zupacademy.rayllanderson.casadocodigo.author.model.Author;
import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorPostRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @UniqueValue(domainClass = Author.class, field = "email")
    private String email;

    @NotBlank
    @Size(max = 400)
    private String description;

    public Author toAuthor() {
        return new Author(name, email, description);
    }

    public AuthorPostRequest(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(max = 400)String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }
}
