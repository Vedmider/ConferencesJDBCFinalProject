package com.study.servlet.command;

import com.study.servlet.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.servlet.constant.PathConstants.LOG_IN_PAGE;

public class LogInCommand implements Command{
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(LOG_IN_PAGE);
    }
}
