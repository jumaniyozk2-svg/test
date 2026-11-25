package com.dasturchi.config;

import com.dasturchi.entity.Role;
import com.dasturchi.entity.User;
import com.dasturchi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Dastur ishga tushganda boshlang'ich ma'lumotlarni (admin va test user) yaratadi.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
//kjsjsjsjsjsjsj
    @Override
    public void run(String... args) {
        createIfNotExists("Administrator", "admin", "admin123", Role.ROLE_LIBRARIAN);
        createIfNotExists("Test User", "user", "user123", Role.ROLE_USER);
    }

    private void createIfNotExists(String fullName, String username, String rawPassword, Role role) {
        if (!userRepository.existsByUsername(username)) {
            userRepository.save(new User(fullName, username, passwordEncoder.encode(rawPassword), role));
            log.info("DataLoader: '{}' ({}) yaratildi", username, role);
        }
    }
}
