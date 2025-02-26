package com.parishservice.parishservice.Config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomHeaderFilter implements jakarta.servlet.Filter {

    private static final String CUSTOM_HEADER_NAME = "X-Requested-By";
    private static final String CUSTOM_HEADER_VALUE = "MyApiParish";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No need to call super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestURI = httpServletRequest.getRequestURI();

        // Skip custom header validation for the /actuator/** and /api/parish/** paths
        if (requestURI.startsWith("/actuator") || requestURI.startsWith("/api/v1/members/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Comment this block temporarily
         String customHeader = httpServletRequest.getHeader(CUSTOM_HEADER_NAME);
        if (CUSTOM_HEADER_VALUE.equals(customHeader)) {
             filterChain.doFilter(servletRequest, servletResponse);
         } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Invalid or missing custom header");
         }
    }

    @Override
    public void destroy() {
        // No need to call super.destroy();
    }
}
