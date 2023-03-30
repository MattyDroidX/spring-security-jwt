package com.security.jwt.security.payload;

import lombok.*;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
