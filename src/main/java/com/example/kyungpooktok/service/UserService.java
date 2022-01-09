package com.example.kyungpooktok.service;

import com.example.kyungpooktok.domain.User.User;
import com.example.kyungpooktok.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userrepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(User user){
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return userrepository.save(user).getId();
    }

}
