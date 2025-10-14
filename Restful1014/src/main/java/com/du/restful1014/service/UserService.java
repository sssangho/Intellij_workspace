package com.du.restful1014.service;

import com.du.restful1014.dto.UserDto;
import com.du.restful1014.entity.User;
import com.du.restful1014.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 생성
    public UserDto createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser);
    }

    // 사용자 1명 조회
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;  // 또는 new UserDto(new User()) 로 대체 가능
        }
        return new UserDto(user);
    }

    // 전체 사용자 목록 조회
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();

        for (User user : userList) {
            result.add(new UserDto(user));
        }

        return result;
    }

    // 사용자 정보 수정
    public UserDto updateUser(Long id, String name, String email) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }

        user.setName(name);
        user.setEmail(email);
        User updatedUser = userRepository.save(user);
        return new UserDto(updatedUser);
    }

    // 사용자 삭제
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }
}
