package com.ttech.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ttech.jwt.security.AuthTokenFilter;
import com.ttech.jwt.security.exception.JwtAuthEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		//jsr250Enabled = true, 
		//securedEnabled = true,
		prePostEnabled = true)
@ComponentScan(basePackages = {"com.tech", "com.ttech"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	 @Autowired
	    private JwtAuthEntryPoint unauthorizedHandler;
	 
		/*
		 * @Autowired UserDetailsServiceImpl userDetailsService;
		 */
	 
		/*
		 * @Override public void configure(AuthenticationManagerBuilder
		 * authenticationManagerBuilder) throws Exception {
		 * authenticationManagerBuilder.userDetailsService(userDetailsService).
		 * passwordEncoder(passwordEncoder()); }
		 */
	 
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
		.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable();
		// Add a filter to validate the tokens with every request
		//http.addFilter(authenticationFilter());
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
		
	@Bean
	public AuthTokenFilter authenticationFilter() throws Exception {
		return new AuthTokenFilter();
	    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
