package com.study.servlet.command;

import com.study.servlet.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.servlet.constant.PathConstants.SLASH_INDEX;


public class LogOutCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Page( SLASH_INDEX, true);
    }
}
