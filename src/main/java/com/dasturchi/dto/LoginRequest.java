package com.dasturchi.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Username kiritilishi shart")
        String username,

        @NotBlank(message = "Parol kiritilishi shart")
        String password
) {
}
