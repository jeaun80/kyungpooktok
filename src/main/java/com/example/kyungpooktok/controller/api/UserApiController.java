package com.example.kyungpooktok.controller.api;

import com.example.kyungpooktok.config.auth.PrincipalDetail;
import com.example.kyungpooktok.domain.User.User;
import com.example.kyungpooktok.dto.User.UserSignupDto;
import com.example.kyungpooktok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    @PostMapping("/auth/api/signup")
    public Long save(@RequestBody UserSignupDto usersignupdto){
        return userService.save(usersignupdto.toEntity());
    }

    @PutMapping("/api/update")
    public Long update(@RequestBody User user, @AuthenticationPrincipal PrincipalDetail principalDetail){
        userService.update(user,principalDetail);
        return user.getId();
    }

    @DeleteMapping("/api/delete")
    public Long delete(@RequestBody User user,@AuthenticationPrincipal PrincipalDetail principalDetail){
        userService.delete(user,principalDetail);
        return user.getId();
    }
}
