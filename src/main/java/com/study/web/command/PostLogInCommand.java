package com.study.web.command;

import com.study.service.LoginService;
import com.study.web.DTO.UserDTO;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static com.study.web.constant.ContentConstants.*;
import static com.study.web.constant.PathConstants.LOG_IN_PAGE;
import static com.study.web.constant.PathConstants.SLASH_INDEX;

public class PostLogInCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(PostLogInCommand.class);

    @Override
    public Page perform(HttpServletRequest request) {
        LoginService loginService = new LoginService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO userDTO = loginService.performLogin(login, password);

        if (userDTO != null) {
            LOG.info("User ID: {} successfully logged in.", userDTO.getId());
            request.getSession().setAttribute(USER, userDTO);
            request.getSession().setAttribute(ROLE, userDTO.getRole().getRoleTitle().toUpperCase());
            return new Page(SLASH_INDEX, true);
        }

        request.setAttribute(REQUEST_ALERT, "Wrong login or password. Please try again");
        request.getSession().setAttribute("login_alert", "Wrong login or password. Please try again");
        return new Page(LOG_IN_PAGE);
    }

}
