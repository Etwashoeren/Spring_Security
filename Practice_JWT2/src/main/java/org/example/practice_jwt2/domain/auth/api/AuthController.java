package org.example.practice_jwt2.domain.auth.api;

import org.example.practice_jwt2.domain.auth.applicatioin.AuthService;
import org.example.practice_jwt2.domain.auth.dto.AuthRequestDTO;
import org.example.practice_jwt2.domain.member.domain.entity.Member;
import org.example.practice_jwt2.domain.member.dto.MemberDTO;
import org.example.practice_jwt2.global.jwt.dto.TokenInfo;
import org.example.practice_jwt2.global.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/member")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseTemplate<Member> register(@RequestBody MemberDTO memberDTO) {
        Member member = authService.register(memberDTO);

        return new ResponseTemplate<>(HttpStatus.OK, "회원가입 성공", member);
    }

    @PostMapping("/login")
    public ResponseTemplate<TokenInfo> login(@RequestBody AuthRequestDTO authRequestDTO) {
        TokenInfo tokenInfo = authService.login(authRequestDTO);

        return new ResponseTemplate<>(HttpStatus.OK, "로그인 완료", tokenInfo);
    }

    @PostMapping("/reissue")
    ResponseTemplate<TokenInfo> reissue(@RequestHeader("Refresh") String refreshToken) {

        TokenInfo newToken = authService.refresh(refreshToken);

        return new ResponseTemplate<>(HttpStatus.OK, "토큰 발급 성공", newToken);
    }
}
