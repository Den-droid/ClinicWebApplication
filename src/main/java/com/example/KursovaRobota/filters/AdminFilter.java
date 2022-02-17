package com.example.KursovaRobota.filters;

import com.example.KursovaRobota.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = false;
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                isLoggedIn = user.getType().equals("Admin");
            }
        }

        String loginURI = httpRequest.getContextPath() + "/login";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        if (isLoggedIn && isLoginRequest) {
            httpResponse.sendRedirect("/admin");
        } else if (isLoggedIn || isLoginRequest) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.sendRedirect("/login");
        }
    }
}
