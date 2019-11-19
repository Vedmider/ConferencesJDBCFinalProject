package com.study.domain;

import com.study.persistence.DTO.UserDTO;

public class Manager {
    private LoginService loginService = new LoginService();


    public UserDTO performLogin (String login, String password){
        return loginService.performLogin(login, password);
    }
}
