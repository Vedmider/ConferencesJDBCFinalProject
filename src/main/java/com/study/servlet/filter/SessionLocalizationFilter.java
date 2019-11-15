package com.study.servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.study.servlet.constant.ContentConstants.BUNDLE;
import static com.study.servlet.constant.ContentConstants.LOCALE;
import static com.study.servlet.constant.PathConstants.ROOT_FILTER_URL;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {ROOT_FILTER_URL},
initParams = {
        @WebInitParam(name = LOCALE, value = "en"),
        @WebInitParam(name = BUNDLE, value = "message")
})
public class SessionLocalizationFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(SessionLocalizationFilter.class);
    private String defaultLocale;
    private String defaultBundle;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultLocale = filterConfig.getInitParameter(LOCALE);
        this.defaultBundle = filterConfig.getInitParameter(BUNDLE);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        LOG.info("Setting locale and bundle attribute to session");
        if (httpServletRequest.getParameter("sessionLocale") != null) {
            session.setAttribute(LOCALE, httpServletRequest.getParameter("sessionLocale"));
        } else {
            setLocale(session);
            setBundle(session);
        }

        chain.doFilter(request, response);
    }

    public void destroy() {}

    private void setLocale(HttpSession session) {
        String locale = (String) session.getAttribute(LOCALE);
        if (locale == null) {
            LOG.info("Set locale to session");
            session.setAttribute(LOCALE, defaultLocale);
        }
    }

    private void setBundle(HttpSession session) {
        String bundle = (String) session.getAttribute(BUNDLE);
        if (bundle == null) {
            LOG.info("Set bundle to session");
            session.setAttribute(BUNDLE, defaultBundle);
        }
    }
}
