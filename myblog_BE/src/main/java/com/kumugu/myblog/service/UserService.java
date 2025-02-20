package com.kumugu.myblog.service;

import com.kumugu.myblog.domain.User;
import com.kumugu.myblog.exception.AppException;
import com.kumugu.myblog.exception.ErrorCode;
import com.kumugu.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    // 회원가입
    public String join(String username, String password, String email) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                throw new AppException(ErrorCode.USERNAME_DUPLICATED, username + "는 이미 있습니다.");
        });

        User user = User.builder()
                .username(username)
                .password(encoder.encode(password))
                .email(email)
                .build();
        userRepository.save(user);

        return "성공!";
    }

    // 로그인
    public String login(String username, String password) {
       User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));
       if (!encoder.matches(password, user.getPassword())) {
           throw new AppException(ErrorCode.INVALID_PASSWORD, "비밀번호가 일치하지 않습니다.");
       }
       return "로그인 성공";
    }
}
