package com.omer.simplelogin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegister {
    private String username;
    private String password;
    private String email;
}
