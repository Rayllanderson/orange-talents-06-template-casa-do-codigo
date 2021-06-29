package br.com.zupacademy.rayllanderson.casadocodigo.state.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.state.model.State;
import br.com.zupacademy.rayllanderson.casadocodigo.state.requests.StatePostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.state.validators.StateUniquePerCountryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/states")
public class SaveStateController {

    @PersistenceContext
    private final EntityManager manager;
    private final StateUniquePerCountryValidator countryValidator;

    @Autowired
    public SaveStateController(EntityManager manager, StateUniquePerCountryValidator countryValidator) {
        this.manager = manager;
        this.countryValidator = countryValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(countryValidator);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid StatePostRequest request){
        State stateToBeSaved = request.toState(manager);
        manager.persist(stateToBeSaved);
        return ResponseEntity.ok().build();
    }
}
