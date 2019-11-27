package com.study.web.command;

import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.web.constant.PathConstants.FORBIDDEN_PAGE;

public class ForbiddenCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(FORBIDDEN_PAGE);
    }
}
