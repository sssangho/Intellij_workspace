package com.du.security2_1015.util;

import com.du.security2_1015.entity.User;
import com.du.security2_1015.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitDataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void initUsers() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User(
                    "admin",
                    passwordEncoder.encode("1234"),
                    "ADMIN"
            );
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user1").isEmpty()) {
            User user = new User(
                    "user1",
                    passwordEncoder.encode("1234"),
                    "USER"
            );
            userRepository.save(user);
        }
    }
}

