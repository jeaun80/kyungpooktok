package com.example.kyungpooktok.dto.User;


import com.example.kyungpooktok.domain.User.Role;
import com.example.kyungpooktok.domain.User.User;
import lombok.*;

import javax.persistence.Column;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserSignupDto {
    private String username;
    private String password;
    private String email;
    private String kakaotolkid;
    private String instarid;
    private Role role;
    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .kakaotalkid(kakaotolkid)
                .instarid(instarid)
                .role(Role.USER)
                .build();
    }



}
