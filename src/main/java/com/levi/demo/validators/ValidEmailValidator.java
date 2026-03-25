package com.levi.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

   private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";

   public boolean isValid(String email, ConstraintValidatorContext context) {
      return StringUtils.isNotBlank(email) && email.matches(EMAIL_REGEX);
   }
}
