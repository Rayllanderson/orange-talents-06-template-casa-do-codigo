package br.com.zupacademy.rayllanderson.casadocodigo.client.requests;

import br.com.zupacademy.rayllanderson.casadocodigo.client.model.Client;
import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.CPFOrCNPJ;
import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.Exists;
import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.UniqueValue;
import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import br.com.zupacademy.rayllanderson.casadocodigo.state.model.State;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClientPostRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Email @NotBlank
    @UniqueValue(domainClass = Client.class, field = "email")
    private String email;

    @NotBlank
    private String telephone;

    @CPFOrCNPJ @NotBlank
    @UniqueValue(domainClass = Client.class, field = "document")
    private String document;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String cep;

    @NotBlank
    private String city;

    private Long stateId;

    @NotNull @Exists(domainClass = Country.class, field = "id")
    private Long countryId;

    @JsonCreator
    public ClientPostRequest(String name, String lastName, String email, String telephone, String document, String address, String complement, String cep, String city, Long stateId, Long countryId) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.cep = cep;
        this.city = city;
        this.stateId = stateId;
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Client toClient(EntityManager manager){
        Country country = manager.find(Country.class, countryId);
        Client client = new Client(name, lastName, email, telephone, document, address, complement, cep, city, country);
        if (stateId != null) {
            State state = manager.find(State.class, stateId);
            client.setState(state);
        }
        return client;
    }
}
