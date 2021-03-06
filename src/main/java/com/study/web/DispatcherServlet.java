package com.study.web;

import com.study.web.command.Command;
import com.study.web.command.CommandFactory;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.study.web.constant.ContentConstants.LOCALE;

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
        req.setAttribute("origRequestURL", req.getRequestURL());

        Command command = CommandFactory.getCommand(path, req.getMethod());
        Page page = command.perform(req);
        setLocale(req);

        if (page.isRedirect()) {
            LOG.info("Redirect to page URL {}", page.getUrl());
            resp.sendRedirect(req.getContextPath() + page.getUrl());
        } else if (page.isAjax()) {
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(page.getAjaxContent());
            out.flush();
        } else {
            LOG.info("Forward to page URL {}", page.getUrl());
            req.getRequestDispatcher("/WEB-INF" + page.getUrl()).forward(req, resp);
        }
    }

    private String getPath(HttpServletRequest req) {
        String requestUri = req.getRequestURI();
        int lastPath = requestUri.lastIndexOf('/');
        String path = requestUri.substring(lastPath);
        LOG.info("Path: " + path);
        return path;
    }

    private void setLocale(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getParameter("sessionLocale") != null) {
            LOG.info("Setting locale and bundle attribute to session. Set locale: {}", httpServletRequest.getParameter("sessionLocale"));
            httpServletRequest.getSession().setAttribute(LOCALE, httpServletRequest.getParameter("sessionLocale"));
        }
    }
}
