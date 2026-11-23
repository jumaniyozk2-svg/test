package com.dasturchi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Token ishlayotganini tekshirish uchun himoyalangan endpointlar.
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/user/me")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of(
                "username", authentication.getName(),
                "roles", authentication.getAuthorities().toString()
        );
    }

    @GetMapping("/admin/hello")
    public Map<String, String> adminHello() {
        return Map.of("message", "Salom, admin!");
    }
}
