package com.dougdeveloper.peoplemanagement.infraestrutura.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI customizeOpenAPI() {
		OpenAPI api = new OpenAPI();
		Info info = new Info();
		info.setTitle("PeopleManagement");
		info.setVersion("1.0.0");
		info.setDescription("API desenvolvida para gerenciamento de pessoas e endereços");
		api.setInfo(info);
		return api;
	}

}
