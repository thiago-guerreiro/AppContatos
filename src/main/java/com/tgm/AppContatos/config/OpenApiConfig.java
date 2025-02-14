package com.tgm.AppContatos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
    public OpenAPI customOpenAPI() {        
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("AppContatos")
                        .description("API Rest para gerenciar um sistema de cadastro de pessoas e seus respectivos contatos, onde cada pessoa pode ter vários contatos")
                        .contact(new Contact().name("Thiago Guerreiro")) //.email("tgm.86@outlook.com").url("URL"))
                        .version("v1"));
    }

}
