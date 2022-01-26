package com.example.kyungpooktok.dto.User;


import com.example.kyungpooktok.domain.User.Role;
import com.example.kyungpooktok.domain.User.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserSignupDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email@NotNull
    private String email;

    private String kakaotalkid;
    private String instarid;
    private Role role;
    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .kakaotalkid(kakaotalkid)
                .instarid(instarid)
                .role(Role.USER)
                .build();
    }



}
