package com.example.kyungpooktok.controller;

import com.example.kyungpooktok.domain.User.User;
import com.example.kyungpooktok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("title",principal.getName());
        return "main";
    }
    @GetMapping("/user/chat")
    public String chat(Model model,Principal principal){
        model.addAttribute("userid", principal.getName());
        return "layout/chat/randomchat";
    }
}