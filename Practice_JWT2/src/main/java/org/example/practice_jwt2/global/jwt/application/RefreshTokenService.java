package org.example.practice_jwt2.global.jwt.application;

import org.example.practice_jwt2.global.jwt.domain.entity.RefreshToken;
import org.example.practice_jwt2.global.jwt.domain.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public void save(String username, String refreshToken) {
        RefreshToken refresh = new RefreshToken();
        refresh.setUsername(username);
        refresh.setRefreshToken(refreshToken);
        refreshTokenRepository.save(refresh);
    }

    public void delete(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }
}
