package com.levi.demo.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidNickNameValidatorTest {

    private ValidNickNameValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ValidNickNameValidator();
    }

    @Test
    void nickname_containing_creative_passes() {
        assertTrue(validator.isValid("creative_musician", null));
    }

    @Test
    void nickname_with_uppercase_creative_passes() {
        assertTrue(validator.isValid("CREATIVE_STAR", null));
    }

    @Test
    void nickname_with_mixed_case_creative_passes() {
        assertTrue(validator.isValid("TheCrEaTiVeOne", null));
    }

    @Test
    void null_nickname_fails() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void blank_nickname_fails() {
        assertFalse(validator.isValid("  ", null));
    }

    @Test
    void nickname_without_creative_fails() {
        assertFalse(validator.isValid("rockstar99", null));
    }

    @Test
    void empty_nickname_fails() {
        assertFalse(validator.isValid("", null));
    }
}
