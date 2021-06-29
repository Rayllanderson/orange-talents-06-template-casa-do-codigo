package br.com.zupacademy.rayllanderson.casadocodigo.country.requests;

import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.UniqueValue;
import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CountryPostRequest {

    @NotBlank
    @UniqueValue(domainClass = Country.class, field = "name")
    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CountryPostRequest(String name) {
        this.name = name;
    }

    public Country toCountry() {
        return new Country(name);
    }
}
