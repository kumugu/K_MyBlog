package com.kumugu.myblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String username;
    private String password;
    private String email;
}
