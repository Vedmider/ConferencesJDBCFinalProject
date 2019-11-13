package com.study.servlet;

import com.study.servlet.command.Command;
import com.study.servlet.command.CommandFactory;
import com.study.servlet.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/app/*")
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Processing doGet Method in dispatcher servlet. Path URI - {}, URL - {}, contextPath {}", req.getRequestURI(), req.getRequestURL(), req.getContextPath());
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Processing doPost Method in dispatcher servlet. Path URI - {}, URL - {}, contextPath {}", req.getRequestURI(), req.getRequestURL(), req.getContextPath());
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getPath(req);

        Command command = CommandFactory.getCommand(path, req.getMethod());

        Page page = command.perform(req);

        if (page.isRedirect()) {
            LOG.info("Redirect to page URL {}", page.getUrl());
            resp.sendRedirect(page.getUrl());
        } else {
            LOG.info("Forward to page URL {}", page.getUrl());
            req.getRequestDispatcher(page.getUrl()).forward(req, resp);
        }
    }

    private String getPath(HttpServletRequest req) {
        String requestUri = req.getRequestURI();
        int lastPath = requestUri.lastIndexOf('/');
        String path = requestUri.substring(lastPath);
        LOG.info("Path: " + path);
        return path;
    }
}
