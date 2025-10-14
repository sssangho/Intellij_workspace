package com.du.restful1014.controller;

import com.du.restful1014.dto.UserDto;
import com.du.restful1014.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController0 {

    private final UserService userService;

    // 사용자 생성
    @PostMapping
    public UserDto createUser(@RequestParam String name,
                              @RequestParam String email) {
        return userService.createUser(name, email);
    }

    // 단일 사용자 조회
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 전체 사용자 조회
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    // 사용자 수정
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String email) {
        return userService.updateUser(id, name, email);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        // 별도 응답 없음 → 기본적으로 200 OK 또는 204 No Content
    }
}
