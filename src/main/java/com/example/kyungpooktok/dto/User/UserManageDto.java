package com.example.kyungpooktok.dto.User;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserManageDto {

    @NotEmpty@Email
    private String email;

    private String kakaotalkid;
    private String instarid;

}