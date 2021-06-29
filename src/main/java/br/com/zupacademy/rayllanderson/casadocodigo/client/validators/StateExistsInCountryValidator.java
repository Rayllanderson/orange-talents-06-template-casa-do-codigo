package br.com.zupacademy.rayllanderson.casadocodigo.client.validators;

import br.com.zupacademy.rayllanderson.casadocodigo.client.requests.ClientPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import br.com.zupacademy.rayllanderson.casadocodigo.state.model.State;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class StateExistsInCountryValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ClientPostRequest.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ClientPostRequest client = (ClientPostRequest) object;
        if (errors.hasErrors() || client.getStateId() == null) return;

        State state = manager.find(State.class, client.getStateId());
        Assert.state(state != null, "Estado informado não existe");
        Country country = manager.find(Country.class, client.getCountryId());

        if(state.belongToThatCountry(country)) return;

        String stateName = state.getName();
        String countryName = country.getName();
        errors.reject("stateId", null, "O Estado " + stateName + " não pertence ao País " + countryName);
    }
}
