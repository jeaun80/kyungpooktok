package com.example.kyungpooktok.controller.api;

import com.example.kyungpooktok.dto.User.UserSignupDto;
import com.example.kyungpooktok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    @PostMapping("/auth/api/signup")
    public Long save(@RequestBody UserSignupDto usersignupdto){
        return userService.save(usersignupdto.toEntity());
    }

}
