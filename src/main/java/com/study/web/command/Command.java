package com.study.web.command;

import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Page perform(HttpServletRequest request);
}
