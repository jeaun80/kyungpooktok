package com.example.kyungpooktok.config.auth;

import com.example.kyungpooktok.domain.User.User;
import com.example.kyungpooktok.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal =  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. " + username));
        return new PrincipalDetail(principal);//findbyusername은 findby+username으로 메소드명에서 jpa레파지토리가 해석한다.
    }
}