package com.ttech.jwt.security;

import java.security.Principal;

public interface MyPrincipal extends Principal {
	
	public String getUserId();
	
	public  String getSessionId();
	
	public String getAccessRole();

}
