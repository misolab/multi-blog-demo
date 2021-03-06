package net.joins.webapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import net.joins.webapp.exception.ForbiddenException;
import net.joins.webapp.vo.ApiResponse;

public class AuthenticationErrorHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        String message = authException.getLocalizedMessage();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ApiResponse apiResponse = ApiResponse.error(new ForbiddenException(message));
        new ObjectMapper().writeValue(response.getWriter(), apiResponse);
    }
}
