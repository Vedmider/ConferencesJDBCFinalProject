package com.study.servlet.command;

import com.study.servlet.constant.PathConstants;
import com.study.servlet.data.Page;

import javax.servlet.http.HttpServletRequest;

public class ConferencesCommand implements Command {


    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(PathConstants.START_PAGE);
    }
}
