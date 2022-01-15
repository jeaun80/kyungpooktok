package com.example.kyungpooktok.service;

import com.example.kyungpooktok.config.auth.PrincipalDetail;
import com.example.kyungpooktok.domain.User.User;
import com.example.kyungpooktok.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userrepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public Long save(User user){
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return userrepository.save(user).getId();
    }

    @Transactional
    public Long update(User user, @AuthenticationPrincipal PrincipalDetail principalDetail){
        User setuser=userrepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + user.getId()));
        setuser.updatepass(bCryptPasswordEncoder.encode(user.getPassword()));
        setuser.updateid(user.getKakaotalkid(),user.getInstarid());
        principalDetail.setUser(setuser); //추가
        return setuser.getId();
    }

    @Transactional
    public Long delete(User user,@AuthenticationPrincipal PrincipalDetail principalDetail){
        User deluser =userrepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("해당회원 없다"+ user.getId()));
        if(bCryptPasswordEncoder.matches(user.getPassword(),deluser.getPassword())==true){
            userrepository.deleteById(user.getId());
        }
        return user.getId();
    }

}
