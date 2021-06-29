package br.com.zupacademy.rayllanderson.casadocodigo.state.requests;

import br.com.zupacademy.rayllanderson.casadocodigo.core.annotations.Exists;
import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import br.com.zupacademy.rayllanderson.casadocodigo.state.model.State;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
        checkIfStateIsUniqueOrThrowIllegalStateException(manager);
        @NotNull Country country = manager.find(Country.class, countryId);
        return new State(name, country);
    }

    /**
     * Confere se o estado já existe no país.
     * @throws IllegalStateException em caso do Estado já existir para o país recebido na requisição
     */
    private void checkIfStateIsUniqueOrThrowIllegalStateException(EntityManager manager) {
        Query query = manager.createQuery("SELECT 1 FROM State s WHERE name = :stateName and s.country.id = :countryId");
        query.setParameter("stateName", name);
        query.setParameter("countryId", countryId);
        List<?> result = query.getResultList();
        Assert.state(result.size() < 1, "Já existe um estado cadastrado nesse país");
    }
}
