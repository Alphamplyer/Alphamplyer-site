package utils.validation.password;

import com.alphamplyer.website.administration.models.users.IUserValidation;

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

