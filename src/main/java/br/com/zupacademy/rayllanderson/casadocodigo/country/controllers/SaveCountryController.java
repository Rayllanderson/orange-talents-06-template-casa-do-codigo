package br.com.zupacademy.rayllanderson.casadocodigo.country.controllers;

import br.com.zupacademy.rayllanderson.casadocodigo.country.model.Country;
import br.com.zupacademy.rayllanderson.casadocodigo.country.requests.CountryPostRequest;
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
@RequestMapping("/countries")
public class SaveCountryController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> save (@RequestBody @Valid CountryPostRequest request){
        Country countryToBeSaved = request.toCountry();
        manager.persist(countryToBeSaved);
        return ResponseEntity.ok().build();
    }
}
