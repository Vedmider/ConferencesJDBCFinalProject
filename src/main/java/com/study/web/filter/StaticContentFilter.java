package com.study.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.study.web.constant.PathConstants.*;

public class StaticContentFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(StaticContentFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LOG.info("Do service, URI: " + httpServletRequest.getRequestURI());
        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        if (shouldBeSkipped(path)) {
            LOG.info("Static resource: " + path);
            chain.doFilter(request, response);
            return;
        }

        String pathToForward = APP_PATH + path;
        LOG.info("Non static resource: " + path + ". New Path: " + pathToForward);

        httpServletRequest.getRequestDispatcher(pathToForward).forward(request, response);

    }

    private boolean shouldBeSkipped(String path) {
        return path.startsWith(RESOURCES_PATH) || path.startsWith(UI_PATH) || path.startsWith(APP_PATH) || path.startsWith(IMG_PATH);
    }

    @Override
    public void destroy() {
    }
}
