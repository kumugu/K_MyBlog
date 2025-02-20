package com.kumugu.myblog.controller;

import com.kumugu.myblog.domain.User;
import com.kumugu.myblog.domain.dto.UserJoinRequest;
import com.kumugu.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<String> join (@RequestBody UserJoinRequest dto) {
        userService.join(dto.getUsername(), dto.getPassword(), dto.getEmail());
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserJoinRequest dto) {
        String loginResult = userService.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok(loginResult);
    }
}
