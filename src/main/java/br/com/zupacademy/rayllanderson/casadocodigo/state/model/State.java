package br.com.zupacademy.rayllanderson.casadocodigo.state.model;

import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne
    private Country country;

    @Deprecated
    public State() {
    }

    public State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public boolean belongToThatCountry(Country country){
        return this.country.equals(country);
    }

    public String getName() {
        return name;
    }
}
