package com.study.service;

import com.study.web.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    private static final AdministrationService administrationService = new AdministrationService();

    public UserDTO performLogin (String login, String password){
        UserDTO user = getUserDTO(login, password);

        if (user != null){
            LOG.info("userEntity login {} password {} id {}", user.getLogin(), user.getPassword(), user.getId());
            return user;
        }
        return null;
    }

    private UserDTO getUserDTO(String login, String password){
        return  administrationService.getAllUsers()
                .stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .filter(user -> user.getPassword().equals(password))
                .findAny().orElse(null);
    }

}
