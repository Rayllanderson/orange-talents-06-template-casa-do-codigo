package br.com.zupacademy.rayllanderson.casadocodigo.core.annotations;

import br.com.zupacademy.rayllanderson.casadocodigo.core.validators.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePerCountry {

    String message() default "{Campo deve ser único}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String countryId();
}
