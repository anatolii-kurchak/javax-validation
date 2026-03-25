package com.levi.demo.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidEmailValidatorTest {

    private ValidEmailValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ValidEmailValidator();
    }

    @Test
    void valid_email_passes() {
        assertTrue(validator.isValid("user@example.com", null));
    }

    @Test
    void valid_email_with_subdomain_passes() {
        assertTrue(validator.isValid("user@mail.example.co.uk", null));
    }

    @Test
    void valid_email_with_plus_passes() {
        assertTrue(validator.isValid("user+tag@example.com", null));
    }

    @Test
    void null_email_fails() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void blank_email_fails() {
        assertFalse(validator.isValid("   ", null));
    }

    @Test
    void email_without_at_fails() {
        assertFalse(validator.isValid("userexample.com", null));
    }

    @Test
    void email_without_domain_fails() {
        assertFalse(validator.isValid("user@", null));
    }

    @Test
    void email_without_tld_fails() {
        assertFalse(validator.isValid("user@example", null));
    }

    @Test
    void email_with_only_at_fails() {
        assertFalse(validator.isValid("@", null));
    }

    @Test
    void email_with_multiple_at_fails() {
        assertFalse(validator.isValid("user@@example.com", null));
    }
}
