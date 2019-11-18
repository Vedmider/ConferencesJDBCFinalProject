package com.study.domain;


import com.study.persistence.DTO.RoleDTO;
import com.study.persistence.DTO.UserDTO;
import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.UserDAO;
import com.study.persistence.entity.User;

public class LoginService {
    private static final UserDAO userDAO = new UserDAO();
    private static final RoleDAO roleDAO = new RoleDAO();

    public UserDTO performLogin (String login, String password){
        UserDTO userDTO = null;
        User userEntity= userDAO.getAll()
                .stream().filter(user -> user.getLogin().equalsIgnoreCase(login))
                .filter(user -> user.getPassword().equals(password))
                .findAny().orElse(null);
        if (userEntity != null){
            RoleDTO roleDTO = new RoleDTO(roleDAO.getById(userEntity.getId()));
            userDTO.setId(userEntity.getId());
            userDTO.setFirstName(userEntity.getFirstName());
            userDTO.setLastName(userEntity.getLastName());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setLogin(userEntity.getLogin());
            userDTO.setPassword(userEntity.getPassword());
            userDTO.setRole(roleDTO);
        }
        return userDTO;
    }
}
