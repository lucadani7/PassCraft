package com.lucadani.model;

public record PasswordConfig(int length, boolean lowercase, boolean uppercase, boolean digits, boolean symbols) {
}
