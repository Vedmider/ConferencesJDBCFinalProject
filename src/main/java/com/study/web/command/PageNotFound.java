package com.study.web.command;

import com.study.web.constant.PathConstants;
import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

public class PageNotFound implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(PathConstants.NOT_FOUND_PAGE);
    }
}
