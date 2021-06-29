package br.com.zupacademy.rayllanderson.casadocodigo.state.requests;

import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.Exists;
import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import br.com.zupacademy.rayllanderson.casadocodigo.state.model.State;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StatePostRequest {
    @NotBlank
    private final String name;

    @NotNull
    @Exists(domainClass = Country.class, field = "id")
    private final Long countryId;

    @JsonCreator
    public StatePostRequest(String name, Long countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public State toState(EntityManager manager){
        @NotNull Country country = manager.find(Country.class, countryId);
        return new State(name, country);
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }
}
