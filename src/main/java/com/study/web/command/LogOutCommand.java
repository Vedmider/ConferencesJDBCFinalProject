package com.study.web.command;

import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.web.constant.PathConstants.SLASH_INDEX;


public class LogOutCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Page( SLASH_INDEX, true);
    }
}
