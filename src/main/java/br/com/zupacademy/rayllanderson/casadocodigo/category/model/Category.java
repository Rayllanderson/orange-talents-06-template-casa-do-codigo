package br.com.zupacademy.rayllanderson.casadocodigo.category.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Deprecated
    public Category(){
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }
}
