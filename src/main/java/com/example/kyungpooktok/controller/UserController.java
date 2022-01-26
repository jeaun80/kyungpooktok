package com.example.kyungpooktok.controller;


import com.example.kyungpooktok.domain.User.User;
import com.example.kyungpooktok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/auth/user/login")
    public String login(){
        return "layout/user/user-login";
    }
    @GetMapping("/auth/user/signup")
    public String Signup(){ return "layout/user/User-SignUp";}

    @GetMapping("/user/management")
    public String manage(Model model,Principal principal){
        Optional<User> res=userService.find(principal.getName());
        if(res.isPresent()){
            User username=res.get();
            model.addAttribute("title",username);
        }
        return "layout/user/User-Management";
    }
    @GetMapping("/user/delete")
    public String delete(){return "layout/user/User-Delete";}

    @GetMapping("/auth/user/update")
    public String update(){
        return "layout/user/User-UpdatePassword";
    }


}

