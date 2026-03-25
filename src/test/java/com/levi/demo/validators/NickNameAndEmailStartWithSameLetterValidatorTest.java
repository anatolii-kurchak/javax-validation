package com.levi.demo.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NickNameAndEmailStartWithSameLetterValidatorTest {

    private NickNameAndEmailStartWithSameLetterValidator validator;

    @BeforeEach
    void setUp() {
        validator = new NickNameAndEmailStartWithSameLetterValidator();
    }

    @Test
    void same_starting_letter_passes() {
        assertTrue(validator.isValid(new Object[]{"alice_creative", "alice@example.com"}, null));
    }

    @Test
    void same_starting_letter_case_insensitive_passes() {
        assertTrue(validator.isValid(new Object[]{"Alice_creative", "alice@example.com"}, null));
    }

    @Test
    void same_starting_uppercase_passes() {
        assertTrue(validator.isValid(new Object[]{"Bob_creative", "Bob@example.com"}, null));
    }

    @Test
    void different_starting_letter_fails() {
        assertTrue(!validator.isValid(new Object[]{"alice_creative", "bob@example.com"}, null));
    }

    @Test
    void null_nickname_fails() {
        assertFalse(validator.isValid(new Object[]{null, "alice@example.com"}, null));
    }

    @Test
    void null_email_fails() {
        assertFalse(validator.isValid(new Object[]{"alice_creative", null}, null));
    }

    @Test
    void blank_nickname_fails() {
        assertFalse(validator.isValid(new Object[]{"  ", "alice@example.com"}, null));
    }

    @Test
    void blank_email_fails() {
        assertFalse(validator.isValid(new Object[]{"alice_creative", "  "}, null));
    }
}
