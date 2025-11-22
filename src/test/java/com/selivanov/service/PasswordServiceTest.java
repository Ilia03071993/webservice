package com.selivanov.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PasswordServiceTest {
    //system under test
    private final PasswordService passwordService = new PasswordService();

    @Test
    void isStrongPassword_whenPasswordIsNull() {
        //Given
        String password = null;

        //Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> passwordService.isStrongPassword(password));
    }

    @Test
    void isStrongPassword_whenPasswordIsShorterThanAllowable() {
        //Given
        String password = "123";

        //When
        boolean strongPassword = passwordService.isStrongPassword(password);

        //Then
        Assertions.assertFalse(strongPassword);
    }

    @Test
    void isStrongPassword_whenPasswordIsNotCorrect() {
        //Given
        String password = "alpha123";

        //When
        boolean strongPassword = passwordService.isStrongPassword(password);

        //Then
        Assertions.assertFalse(strongPassword);
    }

    @Test
    void isStrongPassword_whenPasswordIsCorrect() {
        //Given
        String password = "Alpha123";

        //When
        boolean strongPassword = passwordService.isStrongPassword(password);

        //Then
        Assertions.assertTrue(strongPassword);
    }

    @Test
    void generateTemporaryPassword_shouldHaveLength10() {
        //When
        String password = passwordService.generateTemporaryPassword();

        //Then
        Assertions.assertTrue(password.length() <= 10);
    }

    @Test
    void generateTemporaryPassword_shouldContainOnlyAllowableChars() {
        //Given
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        //When
        String password = passwordService.generateTemporaryPassword();

        //Then
        Assertions.assertTrue(password.matches("[A-Za-z0-9]+"));
    }

    @Test
    void generateTemporaryPassword_passwordsShouldBeDifferent() {
        //Given
        Set<String> passwords = new HashSet<>();

        //When
        for (int i = 0; i < 100; i++) {
            passwords.add(passwordService.generateTemporaryPassword());
        }

        //Then
        assertEquals(100, passwords.size());
    }

    @Test
    void validateNewPassword_whenNewPasswordIsCorrect() {
        //Given
        String oldPassword = "Alpha123";
        String newPassword = "Beta12345";

        //Then
        Assertions.assertDoesNotThrow(() -> passwordService.validateNewPassword(oldPassword, newPassword));
    }

    @Test
    void validateNewPassword_whenNewPasswordTooWeak() {
        //Given
        String oldPassword = "Alpha123";
        String newPassword = "Beta12";

        //Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> passwordService.validateNewPassword(oldPassword, newPassword));
    }

    @Test
    void validateNewPassword_whenNewPasswordMustBeDifferentFromOldPassword() {
        //Given
        String oldPassword = "Alpha123";
        String newPassword = "Alpha123";

        //Then
        Assertions.assertThrows(IllegalStateException.class,
                () -> passwordService.validateNewPassword(oldPassword, newPassword));
    }
}