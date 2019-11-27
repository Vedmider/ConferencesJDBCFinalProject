package com.study.web.command;

import com.google.gson.Gson;
import com.study.persistence.entity.User;
import com.study.service.UserService;
import com.study.web.data.AjaxResponse;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class PostRegistrationCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(PostRegistrationCommand.class);
    private static final UserService userService = new UserService();
    private static Gson gson = new Gson();

    @Override
    public Page perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");

        AjaxResponse ajaxResponse = new AjaxResponse();

        if (login == null || password == null || email == null || firstName == null || lastName == null ) {
            ajaxResponse.setMessage("Empty values! Please, try  again.");
            return getPage(ajaxResponse);
        }

        LOG.debug("Login: {}, password: {}, email: {}", login, password, email);
        boolean exist = userService.isExist(login);

        if (exist ) {
            ajaxResponse.setMessage("Invalid Login! Please, try different login again.");
            return getPage(ajaxResponse);
        }

        Optional<User> userOptional = userService.createUser(login,password,email, firstName, lastName);
        if (userOptional.isPresent()) {
            LOG.info("Registration successful");
        }
        ajaxResponse.setUrl("");
        ajaxResponse.setSuccess(true);
        return getPage(ajaxResponse);
    }

    private Page getPage(AjaxResponse ajaxResponse) {
        return new Page(true, gson.toJson(ajaxResponse));
    }
}

