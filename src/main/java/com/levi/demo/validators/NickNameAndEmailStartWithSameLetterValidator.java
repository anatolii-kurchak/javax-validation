package com.levi.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import org.apache.commons.lang3.StringUtils;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class NickNameAndEmailStartWithSameLetterValidator implements ConstraintValidator<NickNameAndEmailStartWithSameLetter, Object[]> {

   private static final int NICK_NAME = 0;
   private static final int EMAIL = 1;

   public boolean isValid(Object[] parameters, ConstraintValidatorContext context) {
      String nickName = (String) parameters[NICK_NAME];
      String email = (String) parameters[EMAIL];

      if (StringUtils.isAnyBlank(nickName, email)) {
         return false;
      }

      return Character.toLowerCase(nickName.charAt(0)) == Character.toLowerCase(email.charAt(0));
   }
}
