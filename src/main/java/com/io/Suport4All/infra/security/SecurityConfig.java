package com.io.Suport4All.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Configuration
@EnableWebMvc
public class SecurityConfig {
	 @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Autowired
	    SecurityFilter securityFilter;

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .csrf(csrf -> csrf.disable())
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authorizeHttpRequests(authorize -> authorize
	                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
	                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
	                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
	                        .requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
	                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
	                        .requestMatchers(HttpMethod.GET, "/departamento").permitAll()
	                        .requestMatchers(HttpMethod.POST, "/usuario/find/email").permitAll()
	                        .requestMatchers(HttpMethod.POST, "/chamado/create").permitAll()
	                        .requestMatchers(HttpMethod.GET,"/usuarios/all").hasAuthority("ADMIN")
	                        .anyRequest().authenticated()
	                )
	                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
}
