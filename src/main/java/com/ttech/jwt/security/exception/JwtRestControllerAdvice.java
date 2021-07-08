package com.ttech.jwt.security.exception;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = {"com.tech", "com.ttech"})
public class JwtRestControllerAdvice {

	private static Logger log = LoggerFactory.getLogger(JwtRestControllerAdvice.class.getName());
	
	@Autowired
	private HttpServletRequest httpServletRequest;
			
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<ApiResponse> handleException(HttpServletRequest httpServletRequest, AccessDeniedException exception){
		
		log.error("CriticalException caught: {}", exception);
		return getErrorResponseEntity(HttpStatus.FORBIDDEN.value(), "Authentication Failure-This user does not have the sufficient level of access");
	}
	
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiResponse> handleException(HttpServletRequest httpServletRequest, Exception exception){
		
		log.error("CriticalException caught: {}", exception);
		return getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), "");
	}
	
	public static ResponseEntity<ApiResponse> getErrorResponseEntity(
			Integer httpStatusCode,
			String errorMessage) {
		return new ResponseEntity<>( new ApiResponse(httpStatusCode,errorMessage), HttpStatus.valueOf(httpStatusCode));
	}
			
}
