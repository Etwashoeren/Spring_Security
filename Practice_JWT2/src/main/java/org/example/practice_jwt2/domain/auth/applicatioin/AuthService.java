package org.example.practice_jwt2.domain.auth.applicatioin;

import org.example.practice_jwt2.domain.auth.dto.AuthRequestDTO;
import org.example.practice_jwt2.domain.auth.dto.ReissueResponseDTO;
import org.example.practice_jwt2.domain.member.domain.entity.Member;
import org.example.practice_jwt2.domain.member.domain.entity.MemberRole;
import org.example.practice_jwt2.domain.member.domain.repository.MemberRepository;
import org.example.practice_jwt2.domain.member.dto.MemberDTO;
import org.example.practice_jwt2.global.jwt.application.RefreshTokenService;
import org.example.practice_jwt2.global.jwt.domain.entity.RefreshToken;
import org.example.practice_jwt2.global.jwt.domain.repository.RefreshTokenRepository;
import org.example.practice_jwt2.global.jwt.dto.TokenInfo;
import org.example.practice_jwt2.global.jwt.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Member register(MemberDTO memberDTO) {

        if(memberRepository.existsByUsername(memberDTO.getUsername())) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

        Member member = new Member();
        member.setUsername(memberDTO.getUsername());
        member.setNickname(memberDTO.getNickname());
        if(!Objects.equals(memberDTO.getPassword(), memberDTO.getCheckPassword())) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        member.setPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
        member.setRole(MemberRole.MEMBER);
        return memberRepository.save(member);
    }

    public TokenInfo login(AuthRequestDTO authRequestDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String username = authentication.getName();

        String accessToken = jwtTokenProvider.generateAccessToken(username);
        String refreshToken = jwtTokenProvider.generateRefreshToken(username);

        refreshTokenService.save(username, refreshToken);

        return jwtTokenProvider.generateToken(accessToken, refreshToken);
    }

    public TokenInfo refresh(String refreshToken) {

        if(jwtTokenProvider.validateToken(refreshToken)) {
            RefreshToken refresh = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalArgumentException("해당 Refresh_Token 을 찾을 수 없습니다."));

            String newAccessToken = jwtTokenProvider.generateAccessToken(refresh.getUsername());

            String newRefreshToken = jwtTokenProvider.generateRefreshToken(refresh.getUsername());

            refreshTokenService.delete(refresh.getRefreshToken());
            refreshTokenService.save(refresh.getUsername(), newRefreshToken);

            return jwtTokenProvider.generateToken(newAccessToken, newRefreshToken);
        }

        return null;
    }

}
