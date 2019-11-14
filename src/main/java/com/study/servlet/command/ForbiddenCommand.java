package com.study.servlet.command;

import com.study.servlet.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.servlet.constant.PathConstants.FORBIDDEN_PAGE;

public class ForbiddenCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(FORBIDDEN_PAGE);
    }
}
