package br.com.zupacademy.rayllanderson.casadocodigo.state.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.state.model.State;
import br.com.zupacademy.rayllanderson.casadocodigo.state.requests.StatePostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/states")
public class SaveStateController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid StatePostRequest request){
        State stateToBeSaved = request.toState(manager);
        manager.persist(stateToBeSaved);
        return ResponseEntity.ok().build();
    }
}
