package com.alphamplyer.website.administration.utils.validation.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return Email.isValid(email);
    }

}
