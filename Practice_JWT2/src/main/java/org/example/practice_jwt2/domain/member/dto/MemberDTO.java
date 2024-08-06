package org.example.practice_jwt2.domain.member.dto;

import lombok.*;
import org.example.practice_jwt2.domain.member.domain.entity.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String nickname;

    private String username;

    private String password;

    private String checkPassword;
}
