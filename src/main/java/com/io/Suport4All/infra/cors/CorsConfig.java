package com.io.Suport4All.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
	
	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:5500")
				.allowedMethods("GET", "POST", "DELETE", "PUT");
	}
	
	
}
