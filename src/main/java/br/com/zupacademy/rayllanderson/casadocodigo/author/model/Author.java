package br.com.zupacademy.rayllanderson.casadocodigo.author.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email @NotBlank
    private String email;

    @Column(length = 400)
    @NotBlank @Size(max = 400)
    private String description;

    @NotNull
    private LocalDateTime registeredMoment;

    @Deprecated
    public Author() {
    }

    public Author(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(max = 400) String description) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.registeredMoment = LocalDateTime.now();
    }
}
