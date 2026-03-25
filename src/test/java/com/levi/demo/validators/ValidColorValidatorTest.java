package com.levi.demo.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.levi.demo.model.Color;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ValidColorValidatorTest {

    private static Validator validator;

    @Getter
    @Setter
    static class TestBean {
        @ValidColor(colors = {Color.RED, Color.YELLOW})
        private String color;
    }

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void valid_color_red_passes() {
        TestBean bean = new TestBean();
        bean.setColor("RED");
        assertTrue(validator.validate(bean).isEmpty());
    }

    @Test
    void valid_color_yellow_passes() {
        TestBean bean = new TestBean();
        bean.setColor("yellow");
        assertTrue(validator.validate(bean).isEmpty());
    }

    @Test
    void invalid_color_blue_fails() {
        TestBean bean = new TestBean();
        bean.setColor("BLUE");
        Set<ConstraintViolation<TestBean>> violations = validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @Test
    void null_color_fails() {
        TestBean bean = new TestBean();
        bean.setColor(null);
        assertFalse(validator.validate(bean).isEmpty());
    }

    @Test
    void blank_color_fails() {
        TestBean bean = new TestBean();
        bean.setColor("  ");
        assertFalse(validator.validate(bean).isEmpty());
    }

    @Test
    void unknown_color_fails() {
        TestBean bean = new TestBean();
        bean.setColor("GREEN");
        assertFalse(validator.validate(bean).isEmpty());
    }
}
