package com.du.valid1.dto;

import com.du.valid1.entity.MyUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "이름은 반드시 입력해야 합니다.")
    private String name;

    @NotBlank(message = "이메일은 반드시 입력해야 합니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해야 합니다.")
    private String password;

    // DTO -> Entity 변환 메서드
    public MyUser toEntity() {
        return MyUser.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    // Entity로 변환
    public MyUser toEntity(String hashPassword) {
        return MyUser.builder()
                .name(name)
                .email(email)
                .password(hashPassword)
                .build();
    }
}
