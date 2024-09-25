package com.io.Suport4All.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("Swagger").description("Documentação do Suport4All"));
	}
	
}
