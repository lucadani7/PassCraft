package com.lucadani.logic;

public class StrengthCalculator {
    public static String label(int score) {
        return switch (score) {
            case 0, 1 -> "Very Weak";
            case 2 -> "Weak";
            case 3 -> "Medium";
            case 4 -> "Strong";
            default -> "Very Strong";
        };
    }

    public static int calculate(String password) {
        int score = 0;
        if (password.length() >= 8) {
            ++score;
        }
        if (password.length() >= 12) {
            ++score;
        }
        if (password.matches(".*[A-Z].*")) {
            ++score;
        }
        if (password.matches(".*[a-z].*")) {
            ++score;
        }
        if (password.matches(".*\\d.*")) {
            ++score;
        }
        if (password.matches(".*[^a-zA-Z0-9].*")) {
            ++score;
        }
        return Math.min(score, 5);
    }
}
