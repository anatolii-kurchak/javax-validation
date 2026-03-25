package com.levi.demo.validators;

import com.levi.demo.model.MusicianType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ValidMusicianTypeValidator.class)
public @interface ValidMusicianType {
    String message() default "${validatedValue} is invalid musician type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    MusicianType[] types() default {MusicianType.POP, MusicianType.ROCK};
}
