package com.alphamplyer.website.main.utils.validation.password;

import com.alphamplyer.website.main.models.IUserValidation;
import com.alphamplyer.website.main.models.RegisterUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        IUserValidation user = (IUserValidation) obj;
        return user.getPasswordForValidation().equals(user.getMatchingPasswordForValidation());
    }
}

