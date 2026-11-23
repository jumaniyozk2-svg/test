package com.dasturchi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Ism familiya kiritilishi shart")
        String fullName,

        @NotBlank(message = "Username kiritilishi shart")
        @Size(min = 3, max = 50, message = "Username 3 dan 50 gacha belgi bo'lishi kerak")
        String username,

        @NotBlank(message = "Parol kiritilishi shart")
        @Size(min = 6, max = 100, message = "Parol kamida 6 ta belgi bo'lishi kerak")
        String password
) {
}
