package com.vinyl.react_spring.controller;

import com.vinyl.react_spring.dto.LoginRequestDto;
import com.vinyl.react_spring.dto.SignUpRequestDto;
import com.vinyl.react_spring.entity.User;
import com.vinyl.react_spring.repository.UserRepository;
import com.vinyl.react_spring.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return "이미 존재하는 아이디입니다.";
        }

        User user = new User(
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                "ROLE_USER"
        );

        userRepository.save(user);
        return "회원가입 성공!";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return token;
    }
}
