package com.noteCup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.noteCup.member.handler.LogoutHandler;
import com.noteCup.member.service.MemberMM;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberMM mService;
	

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("admin@admin.com")
			.password(new BCryptPasswordEncoder().encode("1234"))
			.roles("ADMIN");
		auth.userDetailsService(mService).passwordEncoder(passwordEncoder());
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
 
		http.authorizeRequests()
				.antMatchers("/", "/registration/**").permitAll()
			.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error=true")
					.permitAll()
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.addLogoutHandler(new LogoutHandler()) // todo
					.logoutSuccessUrl("/login")
					.deleteCookies("JSESSIONID")
					.invalidateHttpSession(true);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
		   .antMatchers("/css/**", "/js/**", "/img/**", "/webjars/**");
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
