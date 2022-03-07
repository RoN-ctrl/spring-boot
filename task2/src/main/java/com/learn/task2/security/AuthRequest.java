package com.learn.task2.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String login;
    private String password;

}
