package com.du.restful1014.dto;

import com.du.restful1014.entity.User;
import lombok.Getter;

@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}

