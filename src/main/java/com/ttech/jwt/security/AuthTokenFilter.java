package com.ttech.jwt.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ttech.jwt.security.utils.JwtUtils;

public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException {
	    try {
	      String jwt = parseJwt(request);
	      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	        String username = jwtUtils.getUserNameFromJwtToken(jwt);
	        String userRole = jwtUtils.getRoleFromJwtToken(jwt);
	        
	        List<GrantedAuthority> authorities =  new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(userRole.substring(1, userRole.length() - 1)));
			
			
	       // UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
	        		authorities);
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        //adding to log information
	        String mdcData = String.format("[%s | %s | %s ]", "USER_ID:"+username, "TRANSACTION_ID:"+UUID.randomUUID().toString(), "URI:"+request.getRequestURI());
	        MDC.put("common-log-data", mdcData);
	      }
	    } catch (Exception e) {
	      logger.error("Cannot set user authentication: {}", e);
	    }

	    filterChain.doFilter(request, response);
	  }
	
	private String parseJwt(HttpServletRequest request) {
	    String accessToken = request.getHeader("Authorization");

	    if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ")) {
	      return accessToken.substring(7, accessToken.length());
	    }

	    return null;
	  }
}
