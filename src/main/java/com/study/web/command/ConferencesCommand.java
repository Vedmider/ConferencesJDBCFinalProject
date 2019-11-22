package com.study.web.command;

import com.study.web.constant.PathConstants;
import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

public class ConferencesCommand implements Command {


    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(PathConstants.START_PAGE);
    }
}
