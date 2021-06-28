package com.ttech.jwt.security;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttech.jwt.model.Course;
import com.ttech.jwt.model.Student;
import com.ttech.jwt.security.exception.ApiResponse;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class.getName());
	
	@PostMapping("/course")
	@RolesAllowed("OFFICE_ADMIN")
	public ResponseEntity<Object> saveCourse(@AuthenticationPrincipal String s,
			@RequestBody Course course) throws Exception {
		logger.info("Entering {} with Parameter :  {}", Thread.currentThread().getStackTrace()[1].getMethodName(), course, s);
		return new ResponseEntity<>( new ApiResponse(HttpStatus.OK.value(),"added"), HttpStatus.CREATED);
	}
	
	@PostMapping("/student")
	@RolesAllowed({"OFFICE_ADMIN", "STUDENT_USER"})
	public ResponseEntity<Object> saveStudent(@RequestBody Student student) throws Exception{
		logger.info("Entering {} with Parameter :  {}", Thread.currentThread().getStackTrace()[1].getMethodName(), student);
		return new ResponseEntity<>( new ApiResponse(HttpStatus.OK.value(),"added"), HttpStatus.CREATED);
	}
	
	@GetMapping("/course")
	public ResponseEntity<Object> getCourseDetails(@AuthenticationPrincipal String s) throws Exception{
		logger.info("Entering {} ", Thread.currentThread().getStackTrace()[1].getMethodName(),s);
		
		
		
		return new ResponseEntity<>( new ApiResponse(HttpStatus.OK.value(),"Courses"), HttpStatus.OK);
	}
}
