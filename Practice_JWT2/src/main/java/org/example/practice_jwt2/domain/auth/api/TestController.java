package org.example.practice_jwt2.domain.auth.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        return "환영합니다 " + username +"님!";
    }
}
