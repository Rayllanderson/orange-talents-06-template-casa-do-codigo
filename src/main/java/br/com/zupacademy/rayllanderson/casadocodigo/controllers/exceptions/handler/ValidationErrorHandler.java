package br.com.zupacademy.rayllanderson.casadocodigo.controllers.exceptions.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    private final MessageSource messageSource;

    @Autowired
    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorOutputDto handleValidationError(MethodArgumentNotValidException exception){
        List<ObjectError> globalErros = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return buildValidationErrorOutputDto(globalErros, fieldErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationErrorOutputDto handleValidationError(BindException exception){
        List<ObjectError> globalErros = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return buildValidationErrorOutputDto(globalErros, fieldErrors);
    }

    private ValidationErrorOutputDto buildValidationErrorOutputDto(List<ObjectError> globalErros, List<FieldError> fieldErrors) {
        ValidationErrorOutputDto validationErrors = new ValidationErrorOutputDto();
        globalErros.forEach(error -> validationErrors.addError(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return validationErrors;
    }

    private String getErrorMessage(ObjectError error){
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}
