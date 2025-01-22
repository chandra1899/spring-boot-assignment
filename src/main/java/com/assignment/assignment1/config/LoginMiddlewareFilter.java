package com.assignment.assignment1.config;

import com.assignment.assignment1.model.Users;
import com.assignment.assignment1.service.CryptoService;
import com.assignment.assignment1.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginMiddlewareFilter implements Filter {
    @Autowired
    private UserService userService;
    @Autowired
    private CryptoService cryptoService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        System.out.println("in filter");
        String urlPath = httpRequest.getRequestURI();
        if(urlPath.equals("/login") || urlPath.equals("/register") || urlPath.startsWith("/h2-console")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }

        String authHeader = httpRequest.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Missing or invalid token");
            return ;
        }
//        String token = authHeader.substring(7);
        String token = getTokenFromCookies(httpRequest);

        if (token == null) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Missing or invalid token");
            return;
        }

        if(!isTokenValid(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Invalid token");
            return ;
        }

        String[] credentials = cryptoService.decryptText(token).split(" ");
//        System.out.println("username : " + credentials[0] + " password : " + credentials[1]);
        Users user = userService.getUserByEmail(credentials[0]);
        httpRequest.setAttribute("user", user);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isTokenValid(String encrptedToken) {
        try {
            String token = cryptoService.decryptText(encrptedToken);
            String[] credentials = token.split(" ");
            if(credentials.length != 2)
                return false;
            Users user = userService.getUserByEmail(credentials[0]);
            if(user == null)
                return false;
            if(!credentials[1].equals(cryptoService.decryptText(user.getPassword())))
                return false;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String getTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
