package com.omer.simplelogin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLogin {
    private String username;
    private String password;
}
