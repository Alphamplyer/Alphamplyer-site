package com.alphamplyer.website.administration.utils.validation.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public void initialize(ValidDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String str_timestamp, ConstraintValidatorContext context){
        return DateUtils.isValid(str_timestamp).x;
    }

}
