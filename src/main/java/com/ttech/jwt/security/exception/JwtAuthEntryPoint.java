package com.ttech.jwt.security.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class.getName());

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e)
            throws IOException, ServletException {
        // Called when the user tries to access an endpoint which requires to be authenticated we just return unauthorizaed
        logger.error("Unauthorized error. Message - {}", e.getMessage());

       ServletServerHttpResponse res = new ServletServerHttpResponse(response);
        res.setStatusCode(HttpStatus.UNAUTHORIZED);
        res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getBody().write(mapper.writeValueAsString(new ApiResponse(HttpStatus.UNAUTHORIZED.value(),"Authentication Failure-The username and password combination is incorrect")).getBytes());
       
        //response.getOutputStream().println(mapper.writeValueAsString(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),"Authentication Failure-The username and password combination is incorrect")));
    }
}