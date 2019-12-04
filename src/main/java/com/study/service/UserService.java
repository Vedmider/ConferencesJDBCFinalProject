package com.study.service;

import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.UserDAO;
import com.study.persistence.entity.User;
import com.study.persistence.mapper.EntityDTOMapper;
import com.study.web.DTO.RoleDTO;
import com.study.web.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.study.persistence.mapper.EntityDTOMapper.mapUser;
import static com.study.web.constant.ContentConstants.USER;

public class UserService implements DBActionsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
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
                .map(user -> EntityDTOMapper.mapUser(user, roleDAO.getById(user.getUserRole()), roleDAO.getRoleRights(user.getUserRole())))
                .collect(Collectors.toList());
    }

    public boolean deleteUser(User user) {
        return userDAO.delete(user);
    }

    public boolean updateUser(User user) {
        return userDAO.update(user);
    }

    public int createUser(User user) {
        return userDAO.create(user);
    }

    @Override
    public void perform(Map<String, String> params) {
        String type = params.get("type");
        User user = mapUserFromParams(params);

        LOG.info("DB Action type: " + type);
        if (type.equalsIgnoreCase("delete")) {
            if (user.getId() != 0) {
                deleteUser(user);
            } else {
                LOG.info("User ID 0. Could not perform delete");
            }
        }
        if (type.equalsIgnoreCase("update")) {
            if (user.getId() != 0) {
                updateUser(user);
            } else {
                LOG.info("User ID 0. Could not perform update");
            }
        }
        if (type.equalsIgnoreCase("create")) {
            if (user.getId() == 0) {
                createUser(user);
            }
        } else {
            LOG.info("User ID not 0. New Report would not be created");
        }
    }

    private User mapUserFromParams(Map<String, String> params) {
        User user;

        if (params.get("id") != null) {
            LOG.info("Setting User ID to {}", params.get("id"));
            user = userDAO.getById(Integer.parseInt(params.get("id")));
        } else {
            user = new User();
        }

        if (params.get("login") != null) {
            user.setLogin(params.get("login"));
        }
        if (params.get("password") != null) {
            user.setPassword(params.get("password"));
        }
        if (params.get("firstName") != null) {
            user.setFirstName(params.get("firstName"));
        }
        if (params.get("lastName") != null) {
            user.setLastName(params.get("lastName"));
        }
        if (params.get("email") != null) {
            user.setEmail(params.get("email"));
        }
        if (params.get("userRole") != null) {
            user.setUserRole(Integer
                    .parseInt(params
                            .get("userRole")));
        }

        return user;
    }
}
