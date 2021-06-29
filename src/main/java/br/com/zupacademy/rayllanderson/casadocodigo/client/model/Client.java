package br.com.zupacademy.rayllanderson.casadocodigo.client.model;

import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.CPFOrCNPJ;
import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import br.com.zupacademy.rayllanderson.casadocodigo.state.model.State;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Email @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String telephone;

    @CPFOrCNPJ @NotBlank
    @Column(nullable = false)
    private String document;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @NotBlank
    @Column(nullable = false)
    private String complement;

    @NotBlank
    @Column(nullable = false)
    private String cep;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @ManyToOne
    private State state;

    @ManyToOne
    @NotNull
    private Country country;

    @Deprecated
    public Client() {
    }

    public Client(String name, String lastName, String email, String telephone, String document, String address,
                  String complement, String cep, String city, Country country) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.cep = cep;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    /**
     * Deve ser usado quando o País selecionado possuir estado.
     */
    public void setState(State state) {
        this.state = state;
    }
}
