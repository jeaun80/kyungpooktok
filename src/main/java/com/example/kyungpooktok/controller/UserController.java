package com.example.kyungpooktok.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "layout/user/user-login";
    }
    @GetMapping("/signup")
    public String Signup(){
        return "layout/user/User-SignUp";
    }
}

