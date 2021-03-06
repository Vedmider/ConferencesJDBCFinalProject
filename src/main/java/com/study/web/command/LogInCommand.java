package com.study.web.command;

import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.web.constant.PathConstants.LOG_IN_PAGE;

public class LogInCommand implements Command{
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(LOG_IN_PAGE);
    }
}
