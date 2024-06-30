package com.Spring.Assignment.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain chain(HttpSecurity security) throws Exception {
		
		return security.authorizeHttpRequests(req->req.requestMatchers("/AddMovie","/Modify","/admin").authenticated().anyRequest().permitAll())
		.httpBasic(Customizer.withDefaults())
		.formLogin(login->login.loginPage("/login").permitAll())
		.logout(logout->logout.permitAll())
		.build();
	}
	
	@Bean
	 UserDetailsService admin() {
		
		UserDetails user = User.withUsername("stella@gmail.com")
				.password("$2a$12$R4J7rvPrZ7g1h9bdPrrkOeL6/eCl2WpcdqEZcmogEpspfR.6OIGDK")
				.roles("Admin")
				.build();
		
		return  new InMemoryUserDetailsManager(user);
		
		
	}
	
	@Bean
	PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
}