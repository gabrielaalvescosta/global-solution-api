package com.projeto.alimentandovidas.entities;

public record RestValidationError(
        Integer code,
        String field,
        String message
) {}