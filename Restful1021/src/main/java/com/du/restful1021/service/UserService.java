package com.du.restful1021.service;

import com.du.restful1021.entity.User;
import com.du.restful1021.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read all
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Read one
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update
    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        });
    }

    // Delete
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

