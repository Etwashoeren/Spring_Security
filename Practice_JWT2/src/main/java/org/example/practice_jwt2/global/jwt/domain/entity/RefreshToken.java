package org.example.practice_jwt2.global.jwt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.practice_jwt2.domain.member.domain.entity.Member;

@Entity
@Getter
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    private String username;
}
