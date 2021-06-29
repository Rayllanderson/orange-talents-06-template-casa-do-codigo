package br.com.zupacademy.rayllanderson.casadocodigo.client.responses;

import br.com.zupacademy.rayllanderson.casadocodigo.client.model.Client;

public class ClientPostResponse {
    private final Long id;

    public ClientPostResponse(Client client) {
        this.id = client.getId();
    }

    public Long getId() {
        return id;
    }
}
