package com.security.jwt.security.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
