package com.du.restful1017.controller;

import com.du.restful1017.entity.User;
import com.du.restful1017.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController0 {

    private final UserService userService;

    // [CREATE] 사용자 생성
//    @RequestBody 는 JSON 형식
//    {
//        "username": "du",
//        "password": "1234"
//    } 형식으로 데이터를 받아준다
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // [READ] 전체 사용자 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // [READ] 단일 사용자 조회
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.orElse(null);  // 존재하지 않으면 null 반환
    }

    // [UPDATE] 사용자 수정
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updated = userService.updateUser(id, user);
        return updated.orElse(null);  // 수정 실패 시 null
    }

    // [DELETE] 사용자 삭제
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "삭제 완료";
    }
}
