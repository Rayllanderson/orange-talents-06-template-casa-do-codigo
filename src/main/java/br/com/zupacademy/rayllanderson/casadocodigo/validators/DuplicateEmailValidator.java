package br.com.zupacademy.rayllanderson.casadocodigo.validators;


import br.com.zupacademy.rayllanderson.casadocodigo.dtos.request.AuthorPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DuplicateEmailValidator implements Validator {

    private final AuthorRepository authorRepository;

    @Autowired
    public DuplicateEmailValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return AuthorPostRequest.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object object, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        AuthorPostRequest possibleAuthor = (AuthorPostRequest) object;
        String errorMessage = "Já existe um Autor cadastrado com este email: " + possibleAuthor.getEmail();
        authorRepository.findByEmail(possibleAuthor.getEmail()).ifPresent(author -> errors.reject("Email", null, errorMessage));
    }
}