package com.snayder.dsclients.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private Contact contact() {
        return new Contact("Esnayder Garcia",
                "https://github.com/EsnayderGarcia/dsclients-api",
                "esnayd.25@gmail.com");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DSCLIENTS API")
                .description("Aplicação para simular um crud de " +
                             "clientes de uma sistema online")
                .version("1.0")
                .contact(contact())
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.snayder.dsclients.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

}
