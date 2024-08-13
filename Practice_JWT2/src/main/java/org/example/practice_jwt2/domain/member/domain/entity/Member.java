package org.example.practice_jwt2.domain.member.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.practice_jwt2.global.jwt.domain.entity.RefreshToken;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

}
