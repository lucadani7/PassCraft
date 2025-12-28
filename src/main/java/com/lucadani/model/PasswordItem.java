package com.lucadani.model;

public record PasswordItem(String value, int strength) {
    @Override
    public String toString() {
        return value + " (" + strength + ")";
    }
}
