package com.study.servlet.command;

import com.study.domain.LoginService;
import com.study.persistence.DTO.UserDTO;
import com.study.servlet.data.Page;

import javax.servlet.http.HttpServletRequest;

public class PostLogInCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        LoginService loginService = new LoginService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO userDTO = loginService.performLogin(login, password);
        if (userDTO != null){

        }

        return null;
    }
}
