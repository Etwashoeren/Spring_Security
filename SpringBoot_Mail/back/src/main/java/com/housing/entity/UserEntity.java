package com.housing.entity;

import com.housing.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity(name = "user")
@Getter
@NoArgsConstructor // 매개변수가 없는 생성자 자동 생성
@AllArgsConstructor // 지정한 모든 필드에 대해서 주입 받는 생성자 생성
public class UserEntity {

    @Id
    private String userId;

    private String password;

    private String email;

    private String type;

    private String role;

    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.type = "app";
        this.role = "ROLE_USER";
    }
}
