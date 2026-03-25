package com.levi.demo.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidNameValidatorTest {

    private ValidNameValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ValidNameValidator();
    }

    @Test
    void alphabetic_name_passes() {
        assertTrue(validator.isValid("Alice", null));
    }

    @Test
    void all_lowercase_passes() {
        assertTrue(validator.isValid("john", null));
    }

    @Test
    void null_name_fails() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void blank_name_fails() {
        assertFalse(validator.isValid("  ", null));
    }

    @Test
    void name_with_numbers_fails() {
        assertFalse(validator.isValid("Alice1", null));
    }

    @Test
    void name_with_spaces_fails() {
        assertFalse(validator.isValid("Alice Bob", null));
    }

    @Test
    void name_with_special_chars_fails() {
        assertFalse(validator.isValid("O'Brien", null));
    }
}
