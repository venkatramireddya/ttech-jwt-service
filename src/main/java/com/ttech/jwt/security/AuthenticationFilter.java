package com.ttech.jwt.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class AuthenticationFilter  extends AbstractPreAuthenticatedProcessingFilter{

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		
		String requestUrl = request.getRequestURI();
		SecurityInfo securityInfo = new SecurityInfo();
			
		try {
			String accessToken = request.getHeader("Authorization");
			
		}catch(Exception e) {
			
		}
		request.setAttribute("security-info", securityInfo);
		
		return true;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
