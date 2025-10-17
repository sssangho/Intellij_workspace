package com.du.newsecurity1017.service;

import com.du.newsecurity1017.entity.User;
import com.du.newsecurity1017.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    // 아래는 참고용으로 더 쉬운 코드를 보여줌
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 1. username으로 사용자 찾기
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//
//        // 2. 없으면 예외 던지기
//        if (optionalUser.isEmpty()) {
//            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
//        }
//
//        // 3. 있으면 꺼내서 변수에 담기
//        User user = optionalUser.get();
//
//        // 4. Spring Security가 이해할 수 있는 User 객체로 감싸서 리턴
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles("USER")  // "ROLE_USER"와 동일
//                .build();
//    }
}
