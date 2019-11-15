package com.study.servlet.command;

import com.study.servlet.data.Page;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Page perform(HttpServletRequest request);
}
