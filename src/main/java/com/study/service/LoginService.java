package com.study.service;


import com.study.persistence.DTO.RoleDTO;
import com.study.persistence.DTO.UserDTO;
import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.UserDAO;
import com.study.persistence.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    private static final UserDAO userDAO = new UserDAO();
    private static final RoleDAO roleDAO = new RoleDAO();

    public UserDTO performLogin (String login, String password){
        User userEntity= getUserEntity(login, password);

        if (userEntity != null){
            LOG.info("userEntity login {} password {} id {}", userEntity.getLogin(), userEntity.getPassword(), userEntity.getId());
            return convert(userEntity);
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

    private UserDTO convert(User userEntity){
        UserDTO userDTO = new UserDTO();
        RoleDTO roleDTO = new RoleDTO(roleDAO.getById(userEntity.getId()));
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(roleDTO);
        return userDTO;
    }
}
