package com.du.restful1014.controller;

import com.du.restful1014.dto.UserDto;
import com.du.restful1014.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController1 {

    private final UserService userService;

    @GetMapping("/test")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
