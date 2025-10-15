package com.du.security2_1015.service;

import com.du.security2_1015.dto.UserDto;
import com.du.security2_1015.entity.User;
import com.du.security2_1015.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserDto userDto) {
        // 중복 사용자 체크
        Optional<User> existing = userRepository.findByUsername(userDto.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        // 암호화해서 저장
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // role 지정 (넘겨오지 않으면 기본 USER)
        String role = userDto.getRole();
        if (role == null || role.isBlank()) {
            role = "USER";
        }
        user.setRole(role);

        return userRepository.save(user);
    }
}

