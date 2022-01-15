package com.example.kyungpooktok.controller;


import com.example.kyungpooktok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class UserController {

    @GetMapping("/auth/user/login")
    public String login(){
        return "layout/user/user-login";
    }
    @GetMapping("/auth/user/signup")
    public String Signup(){ return "layout/user/User-SignUp";}

    @GetMapping("/user/management")
    public String management(){return "layout/user/User-Management";}

    @GetMapping("/user/delete")
    public String delete(){return "layout/user/User-Delete";}


}

