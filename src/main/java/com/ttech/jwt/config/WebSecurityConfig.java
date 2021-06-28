package com.ttech.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.ttech.jwt.security.AuthenticationFilter;
import com.ttech.jwt.security.exception.JwtAuthEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	 @Autowired
	    private JwtAuthEntryPoint unauthorizedHandler;
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/v3/api-docs/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui.html")
		.permitAll()
		.antMatchers(HttpMethod.GET, "/course", "/authentication/course").permitAll()
		.anyRequest().authenticated()
		.and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable();
		// Add a filter to validate the tokens with every request
				http.addFilter(authenticationFilter());
	}
		
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
		
		authBuilder.inMemoryAuthentication().withUser("john_doe").password("{noop}student_password").roles("STUDENT_USER");
		authBuilder.inMemoryAuthentication().withUser("john_doe").password("{noop}admin_password").roles("OFFICE_ADMIN", "STUDENT_USER");
	}
	
	
	@Bean
	public AuthenticationFilter authenticationFilter() throws Exception {
		 AuthenticationFilter filter = new AuthenticationFilter();
	        filter.setAuthenticationManager(authenticationManager());
	        return filter;
	    }
}
