package com.study.service;

import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.UserDAO;
import com.study.persistence.entity.User;
import com.study.persistence.mapper.EntityDTOMapper;
import com.study.web.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LoginService implements DBActionsService{
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    private static final UserDAO userDAO = new UserDAO();
    private static final RoleDAO roleDAO = new RoleDAO();

    public UserDTO performLogin (String login, String password){
        User userEntity= getUserEntity(login, password);

        if (userEntity != null){
            LOG.info("userEntity login {} password {} id {}", userEntity.getLogin(), userEntity.getPassword(), userEntity.getId());
            return EntityDTOMapper.mapUser(userEntity, roleDAO.getById(userEntity.getId()));
        }
        return null;
    }

    private User getUserEntity(String login, String password){
        return  userDAO.getAll()
                .stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .filter(user -> user.getPassword().equals(password))
                .findAny().orElse(null);
    }

    @Override
    public void perform(Map<String, String> params) {

    }
}
