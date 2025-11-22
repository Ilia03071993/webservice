package com.selivanov.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordService {

    public boolean isStrongPassword(String password) {
        if (password == null) throw new IllegalArgumentException("Password cannot be null");
        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*\\d.*");
    }

    public String generateTemporaryPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) sb.append(chars.charAt(random.nextInt(chars.length())));
        return sb.toString();
    }

    public void validateNewPassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword))
            throw new IllegalStateException("New password must differ from the old one");
        if (!isStrongPassword(newPassword))
            throw new IllegalArgumentException("Password too weak");
    }
}
