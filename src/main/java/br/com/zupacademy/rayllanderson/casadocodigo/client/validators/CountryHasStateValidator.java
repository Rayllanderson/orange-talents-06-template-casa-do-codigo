package br.com.zupacademy.rayllanderson.casadocodigo.client.validators;

import br.com.zupacademy.rayllanderson.casadocodigo.client.requests.ClientPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class CountryHasStateValidator implements Validator {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ClientPostRequest.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (errors.hasErrors()) return;
        ClientPostRequest client = (ClientPostRequest) object;
        String jpql = "SELECT COUNT(*) FROM State s WHERE s.country.id = " + client.getCountryId();
        long countResult = (Long) manager.createQuery(jpql).getSingleResult();
        boolean countryHasStateAndStateIdIsNull = countResult > 0 && client.getStateId() == null;
        if (countryHasStateAndStateIdIsNull){
            errors.reject("StateId", null, "O País selecionado possui um ou mais estados. Por favor, selecione um estado");
        }
    }
}
