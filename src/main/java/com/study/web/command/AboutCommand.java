package com.study.web.command;

import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.study.web.constant.PathConstants.ABOUT_PAGE;

public class AboutCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(ABOUT_PAGE);
    }
}
