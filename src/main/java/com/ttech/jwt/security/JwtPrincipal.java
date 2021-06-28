package com.ttech.jwt.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtPrincipal extends AbstractAuthenticationToken implements MyPrincipal {
	
	private static final long serialVersionUID = 8293446677146050980L;

	private String userId;
	
	public  String sessionId;
	
	public String accessRole;
	
	public JwtPrincipal(String userId,
			String sessionId,
			String accessRole,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.userId= userId;
		this.sessionId= sessionId;
		this.accessRole = accessRole;
	}
	@Override
	public Object getCredentials() {
		return "";
	}

	@Override
	public Object getPrincipal() {
		
		return userId;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public String getAccessRole() {
		return accessRole;
	}

}
