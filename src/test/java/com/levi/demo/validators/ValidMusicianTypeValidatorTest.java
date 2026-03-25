package com.levi.demo.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.levi.demo.model.MusicianType;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ValidMusicianTypeValidatorTest {

    private static Validator validator;

    @Getter
    @Setter
    static class TestBean {
        @ValidMusicianType(types = {MusicianType.POP, MusicianType.ROCK})
        private String musicianType;
    }

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void valid_pop_type_passes() {
        TestBean bean = new TestBean();
        bean.setMusicianType("POP");
        assertTrue(validator.validate(bean).isEmpty());
    }

    @Test
    void valid_rock_type_case_insensitive_passes() {
        TestBean bean = new TestBean();
        bean.setMusicianType("rock");
        assertTrue(validator.validate(bean).isEmpty());
    }

    @Test
    void invalid_estrada_type_fails() {
        TestBean bean = new TestBean();
        bean.setMusicianType("ESTRADA");
        Set<ConstraintViolation<TestBean>> violations = validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @Test
    void null_type_fails() {
        TestBean bean = new TestBean();
        bean.setMusicianType(null);
        assertFalse(validator.validate(bean).isEmpty());
    }

    @Test
    void blank_type_fails() {
        TestBean bean = new TestBean();
        bean.setMusicianType("");
        assertFalse(validator.validate(bean).isEmpty());
    }

    @Test
    void unknown_type_fails() {
        TestBean bean = new TestBean();
        bean.setMusicianType("JAZZ");
        assertFalse(validator.validate(bean).isEmpty());
    }
}
