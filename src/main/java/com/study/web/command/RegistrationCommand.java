package com.study.web.command;

import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.web.constant.PathConstants.REGISTRATION_PAGE;

public class RegistrationCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(REGISTRATION_PAGE);
    }
}
