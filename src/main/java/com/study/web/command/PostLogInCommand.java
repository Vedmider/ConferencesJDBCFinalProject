package com.study.web.command;

import com.study.service.JMS.Consumer;
import com.study.service.JMS.Producer;
import com.study.service.Manager;
import com.study.persistence.DTO.UserDTO;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static com.study.service.ServiceConstants.LOGIN_MESSAGE_QUEUE;
import static com.study.web.constant.ContentConstants.*;
import static com.study.web.constant.PathConstants.*;

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
            request.getSession().setAttribute(ROLE, userDTO.getRole().getRoleTitle().toUpperCase());
            return new Page(SLASH_INDEX, true);
        }

        String message = performMQ(request);
        if (message == null){
            LOG.info("Failed to perform message to RabbitMQ");
        }
        request.setAttribute(REQUEST_ALERT, "Wrong login or password. Please try again");
        request.getSession().setAttribute("login_alert", "Wrong login or password. Please try again");
        return new Page(LOG_IN_PAGE);
    }

    private String performMQ (HttpServletRequest request) {
        String login = request.getParameter("login");
        LOG.info("Failed to login with {}.", login);
        Producer.sendMessage("Failed to login!", LOGIN_MESSAGE_QUEUE);
        request.setAttribute(REQUEST_ALERT, "Wrong login or password. Please try again");
        String message = null;

        while (message == null){
            message = Consumer.getMessage(LOGIN_MESSAGE_QUEUE);
        }
        LOG.info("Message is " + message);
        return message;
    }
}
