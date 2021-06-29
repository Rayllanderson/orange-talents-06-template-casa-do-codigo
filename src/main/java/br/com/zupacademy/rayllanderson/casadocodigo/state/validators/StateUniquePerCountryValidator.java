package br.com.zupacademy.rayllanderson.casadocodigo.state.validators;

import br.com.zupacademy.rayllanderson.casadocodigo.state.requests.StatePostRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class StateUniquePerCountryValidator implements Validator {
   
    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(StatePostRequest.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        StatePostRequest request = (StatePostRequest) object; 
        Query query = manager.createQuery("SELECT 1 FROM State s WHERE name = :stateName and s.country.id = :countryId");
        query.setParameter("stateName", request.getName());
        query.setParameter("countryId", request.getCountryId());
        List<?> result = query.getResultList();
        if (result.size() >= 1){
            errors.reject("stateName and countryId", null, "Esse estado já está cadastrado no país selecionado");
        }
    }
}
