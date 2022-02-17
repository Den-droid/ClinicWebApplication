package com.example.KursovaRobota.filters;

import com.example.KursovaRobota.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FrontendFilter implements Filter {
    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;

    private static final String[] loginRequiredURLs = {
            "/user"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        httpRequest = (HttpServletRequest) servletRequest;
        httpResponse = (HttpServletResponse) servletResponse;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/admin/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = false;
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                isLoggedIn = user.getType().equals("Customer");
            }
        }
        String loginURI = httpRequest.getContextPath() + "/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        if (isLoggedIn && isLoginRequest) {
            httpResponse.sendRedirect("/user");
        } else if (!isLoggedIn && isLoginRequired()) {
            httpResponse.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString();
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }
        return false;
    }
}
