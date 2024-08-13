package org.example.practice_jwt2.global.jwt.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.example.practice_jwt2.global.jwt.dto.TokenInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    private SecretKey secretKey;

    private final long expiredMs;

    private final long refreshedMs;

    public JwtTokenProvider(@Value("${spring.jwt.secret}") String secret, @Value("${spring.jwt.expiredMS}") long expiredMS, @Value("${spring.jwt.refreshedMs}") long refreshedMS) {

        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.expiredMs = expiredMS;
        this.refreshedMs = refreshedMS;
    }

    public String generateAccessToken(String username) {

        Date now = new Date();
        Date accessTokenExpiresAt = new Date(now.getTime() + expiredMs);

        return Jwts.builder()
                .claim("username", username)
                .issuedAt(now)
                .expiration(accessTokenExpiresAt)
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Date now = new Date();
        Date refreshTokenExpiresAt = new Date(now.getTime() + refreshedMs);

        return Jwts.builder()
                .claim("username", username)
                .issuedAt(now)
                .expiration(refreshTokenExpiresAt)
                .signWith(secretKey)
                .compact();
    }

    public TokenInfo generateToken(String accessToken, String refreshToken) {

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload();

        String username = claims.get("username", String.class);

        return new UsernamePasswordAuthenticationToken(username, "", Collections.emptyList());
    }

}
