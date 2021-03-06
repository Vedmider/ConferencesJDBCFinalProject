package com.study.web.filter;

import com.study.web.DTO.UserDTO;
import com.study.web.config.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.study.web.constant.PathConstants.SLASH_FORBIDDEN;
import static com.study.web.constant.PathConstants.SLASH_LOGIN;

public class AuthenticationFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String path = getPath(httpServletRequest);

        if (!SecurityConfig.isSecurePage(path)) {
            LOG.info("Page is not secured: " + path);
            chain.doFilter(request, response);
            return;
        }
        String contextPath = httpServletRequest.getContextPath();

        HttpSession session = httpServletRequest.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            LOG.info("User not logged");
            httpServletResponse.sendRedirect(contextPath + SLASH_LOGIN);
            return;
        }

        boolean hasPermission = SecurityConfig.hasPermission(path, user.getRole().getRoleTitle().toUpperCase());

        if (!hasPermission) {
            LOG.info("User has not permission : " + user + " , " + path);
            httpServletResponse.sendRedirect(contextPath + SLASH_FORBIDDEN);
            return;
        }

        LOG.info("User has permission. Continue");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private String getPath(HttpServletRequest req) {
        String requestUri = req.getRequestURI();
        int lastPath = requestUri.lastIndexOf('/');
        String path = requestUri.substring(lastPath);
        LOG.info("Path: " + path);
        return path;
    }
}
