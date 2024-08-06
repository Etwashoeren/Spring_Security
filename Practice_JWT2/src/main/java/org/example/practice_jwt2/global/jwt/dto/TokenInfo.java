package org.example.practice_jwt2.global.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenInfo {

    private String grantType;

    private String accessToken;

    private String refreshToken;
}
