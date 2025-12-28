package com.lucadani.logic;

import com.lucadani.model.PasswordConfig;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate(PasswordConfig cfg) {
        StringBuilder chars = new StringBuilder();
        if (cfg.lowercase()) {
            chars.append(LOWER);
        }
        if (cfg.uppercase()) {
            chars.append(UPPER);
        }
        if (cfg.digits()) {
            chars.append(DIGITS);
        }
        if (cfg.symbols()) {
            chars.append(SYMBOLS);
        }
        if (chars.isEmpty()) {
            return "";
        }
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < cfg.length(); ++i) {
            int idx = RANDOM.nextInt(chars.length());
            password.append(chars.charAt(idx));
        }
        return password.toString();
    }
}
