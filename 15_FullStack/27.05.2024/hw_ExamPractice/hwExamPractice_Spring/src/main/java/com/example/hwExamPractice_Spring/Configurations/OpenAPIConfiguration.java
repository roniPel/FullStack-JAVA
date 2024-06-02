package com.example.hwExamPractice_Spring.Configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenAPI(@Value("Task Management System API") String serviceTitle, @Value("3.0") String serviceVersion) {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Task System api for development");

        // Configure Contact
        Contact myContact = new Contact();
        myContact.setName("Roni Peled");
        myContact.setEmail("roni.rose@gmail.com");

        // Configure Info
        Info info = new Info()
                .title(serviceTitle)
                .version(serviceVersion)
                .description("This API exposes endpoints to manage Task System")
                .contact(myContact);

        final String securitySchemeName = "bearerAuth";


        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(
                                securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .info(info).servers(List.of(server));
    }

}
