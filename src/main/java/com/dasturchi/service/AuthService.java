package com.dasturchi.service;

import com.dasturchi.dto.AuthResponse;
import com.dasturchi.dto.LoginRequest;
import com.dasturchi.dto.RegisterRequest;
import com.dasturchi.entity.Role;
import com.dasturchi.entity.User;
import com.dasturchi.repository.UserRepository;
import com.dasturchi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Bu username band: " + request.username());
        }

        User user = new User(
                request.fullName(),
                request.username(),
                passwordEncoder.encode(request.password()),
                Role.ROLE_USER
        );
        userRepository.save(user);

        return buildResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new IllegalArgumentException("Foydalanuvchi topilmadi"));

        return buildResponse(user);
    }

    private AuthResponse buildResponse(User user) {
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, "Bearer", user.getUsername(), user.getRole().name());
    }
}
