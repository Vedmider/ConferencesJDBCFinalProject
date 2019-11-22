package com.study.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.study.web.constant.ContentConstants.CONTENT_TEXT_HTML_CHARSET_UTF_8;
import static com.study.web.constant.ContentConstants.UTF_8;


public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = UTF_8;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        if (servletRequest.getCharacterEncoding() == null) {
            servletRequest.setCharacterEncoding(encoding);
        }

        servletResponse.setContentType(CONTENT_TEXT_HTML_CHARSET_UTF_8);
        servletResponse.setCharacterEncoding(UTF_8);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
