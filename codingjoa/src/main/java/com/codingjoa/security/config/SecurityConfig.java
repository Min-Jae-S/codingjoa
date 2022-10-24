package com.codingjoa.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.codingjoa.security.service.ValidationFilter;

@Configuration
@ComponentScan("com.codingjoa.security.service")
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationSuccessHandler loginSuccessHandler;
	
	@Autowired
	AuthenticationFailureHandler loginFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*	FilterChain
		 * 
		 *	Browser HTTP Request
		 *		--> SecurityContextPersistenceFilter 
		 * 		--> HeaderWriterFilter
		 * 		--> CsrfFilter
		 * 		--> LogoutFilter
		 * 		--> UsernamePasswordAuthenticationFilter
		 * 		--> ...
		 */
		
		http
			.csrf().disable()
			.addFilterBefore(new CharacterEncodingFilter("UTF-8", true), CsrfFilter.class)
			.addFilterBefore(new ValidationFilter(), UsernamePasswordAuthenticationFilter.class) 
			// Filter를 구현해서 Bean으로 만들면 안되고 인스턴스(new) 형태로 추가 해야한다.
			// Bean으로 만들경우 자동으로 Servlet 필터로 추가됨

			.authorizeRequests()
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/member/login")
				.usernameParameter("memberId")
				.passwordParameter("memberPassword")
				.loginProcessingUrl("/member/loginProc")
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.and()
			.exceptionHandling()
				.accessDeniedPage("/accessDenied");
	}
	
	
}