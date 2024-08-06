package org.example.practice_jwt2.domain.auth.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {

    private String username;

    private String password;

}
