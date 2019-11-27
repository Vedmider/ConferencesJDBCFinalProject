package com.study.service;

import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.UserDAO;
import com.study.persistence.entity.User;
import com.study.persistence.mapper.EntityDTOMapper;
import com.study.web.DTO.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.study.web.constant.ContentConstants.USER;

public class UserService implements DBActionsService {
    private static final UserDAO userDAO = new UserDAO();
    private static final RoleDAO roleDAO = new RoleDAO();

    public Optional<User> validateUser(String login, String password) {
        List<User> all = userDAO.getAll();

        return all.stream()
                .filter(u -> u.getLogin().equals(login)
                        && u.getPassword().equals(password))
                .findFirst();
    }

    public Optional<User> findByLogin(String login) {
        List<User> all = userDAO.getAll();

        return all.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }

    public boolean isExist(String login) {
        List<User> all = userDAO.getAll();

        return all.stream()
                .anyMatch(u -> u.getLogin().equals(login));
    }

    public Optional<User> createUser(String login, String password, String email, String firstName, String lastName) {
        Optional<Integer> userRoleId = getUserRoleId();
        return getUser(login, password, email, firstName, lastName, userRoleId);
    }

    public Optional<User> createUser(String login, String password, String email, String firstName, String lastName, String role) {
        Optional<Integer> userRoleId = getUserRoleId(role);
        return getUser(login, password, email, firstName, lastName, userRoleId);
    }

    private Optional<User> getUser(String login, String password, String email, String firstName, String lastName, Optional<Integer> userRoleId) {
        User user = new User();
        if (userRoleId.isPresent()) {
            user.setUserRole(userRoleId.get());
        }
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        int id = userDAO.create(user);

        return id != 0 ?
                findByLogin(user.getLogin())
                : Optional.empty();
    }

    private Optional<Integer> getUserRoleId() {
        return roleDAO.getAll()
                .stream()
                .filter(role -> role.getTitle()
                        .equalsIgnoreCase(USER))
                .map(role -> role.getId())
                .findFirst();
    }

    private Optional<Integer> getUserRoleId(String roleName) {
        return roleDAO.getAll()
                .stream()
                .filter(role -> role.getTitle()
                        .equalsIgnoreCase(roleName))
                .map(role -> role.getId())
                .findFirst();
    }

    public List<UserDTO> getAll() {
        return userDAO.getAll().stream()
                .map(user -> EntityDTOMapper.mapUser(user, roleDAO.getById(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void perform(Map<String, String> params) {

    }
}
