package br.com.zupacademy.rayllanderson.casadocodigo.validators;


import br.com.zupacademy.rayllanderson.casadocodigo.dtos.request.CategoryPostRequest;
import br.com.zupacademy.rayllanderson.casadocodigo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DuplicateCategoryNameValidator implements Validator {

    private final CategoryRepository categoryRepository;

    @Autowired
    public DuplicateCategoryNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return CategoryPostRequest.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object object, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        CategoryPostRequest possibleDuplicatedCategory = (CategoryPostRequest) object;
        String errorMessage = "Já existe uma categoria cadastrada com este nome: " + possibleDuplicatedCategory.getName();
        categoryRepository.findByName(possibleDuplicatedCategory.getName()).ifPresent(category -> errors.reject("Nome", null, errorMessage));
    }
}