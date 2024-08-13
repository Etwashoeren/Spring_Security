package org.example.practice_jwt2.global.jwt.domain.repository;

import jakarta.transaction.Transactional;
import org.example.practice_jwt2.domain.member.domain.entity.Member;
import org.example.practice_jwt2.global.jwt.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Transactional
    void deleteByRefreshToken(String refreshToken);
}
