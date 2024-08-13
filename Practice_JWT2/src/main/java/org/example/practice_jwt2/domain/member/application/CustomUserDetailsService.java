package org.example.practice_jwt2.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.example.practice_jwt2.domain.member.domain.CustomUserDetails;
import org.example.practice_jwt2.domain.member.domain.entity.Member;
import org.example.practice_jwt2.domain.member.domain.repository.MemberRepository;
import org.example.practice_jwt2.domain.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다. username: " + username)
        );

        return new CustomUserDetails(member);
    }



}
