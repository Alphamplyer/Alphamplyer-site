package com.alphamplyer.website.administration.utils.validation.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimestampValidator implements ConstraintValidator<ValidTimestamp, String> {

    @Override
    public void initialize(ValidTimestamp constraintAnnotation) {
    }
    @Override
    public boolean isValid(String str_timestamp, ConstraintValidatorContext context){
        return TimestampUtils.isValid(str_timestamp).x;
    }

}
