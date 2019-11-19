package com.study.servlet.command;

import com.study.domain.LoginService;
import com.study.domain.Manager;
import com.study.persistence.DTO.UserDTO;
import com.study.servlet.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static com.study.servlet.constant.ContentConstants.*;
import static com.study.servlet.constant.PathConstants.LOG_IN_PAGE;
import static com.study.servlet.constant.PathConstants.START_PAGE;

public class PostLogInCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(PostLogInCommand.class);

    @Override
    public Page perform(HttpServletRequest request) {
        Manager manager = new Manager();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO userDTO = manager.performLogin(login, password);

        if (userDTO != null) {
            LOG.info("User ID: {} successfully logged in.", userDTO.getId());
            request.getSession().setAttribute(USER, userDTO);
            request.getSession().setAttribute(ROLE, userDTO.getRole().getRoleTitle());
            return new Page(START_PAGE, true);
        }

        LOG.info("Failed to login with {}.", login);
        request.setAttribute(REQUEST_ALERT, "Wrong login or password. Please try again");
        return new Page(LOG_IN_PAGE);
    }
}
