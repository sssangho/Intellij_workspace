package com.du.security3_1015.dto;

public class UserDto {
    private String username;
    private String password;
    private String role;

    public UserDto() {}

    public UserDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // getters & setters...
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

