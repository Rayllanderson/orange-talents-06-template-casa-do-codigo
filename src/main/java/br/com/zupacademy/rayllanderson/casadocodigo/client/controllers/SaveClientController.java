package br.com.zupacademy.rayllanderson.casadocodigo.client.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.client.model.Client;
import br.com.zupacademy.rayllanderson.casadocodigo.client.requests.ClientPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.client.responses.ClientPostResponse;
import br.com.zupacademy.rayllanderson.casadocodigo.client.validators.CountryHasStateValidator;
import br.com.zupacademy.rayllanderson.casadocodigo.client.validators.StateExistsInCountryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class SaveClientController {

    @PersistenceContext
    private final EntityManager manager;
    private final StateExistsInCountryValidator stateExistsInCountryValidator;
    private final CountryHasStateValidator countryHasStateValidator;

    @Autowired
    public SaveClientController(EntityManager manager, StateExistsInCountryValidator stateExistsInCountryValidator, CountryHasStateValidator countryHasStateValidator) {
        this.manager = manager;
        this.stateExistsInCountryValidator = stateExistsInCountryValidator;
        this.countryHasStateValidator = countryHasStateValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(countryHasStateValidator);
        binder.addValidators(stateExistsInCountryValidator);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ClientPostResponse> save (@RequestBody @Valid ClientPostRequest request){
        Client clientToBeSaved = request.toClient(manager);
        manager.persist(clientToBeSaved);
        return ResponseEntity.ok(new ClientPostResponse(clientToBeSaved));
    }
}
