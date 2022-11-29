package com.example.irrigation.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * This class is used to configure swagger how we want to use it for documenting of our APIs
 */
@Component
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/v1/*")) //pick only apis following below url convention
                .apis(RequestHandlerSelectors.basePackage("com.example.irrigation"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Irrigation System API documentation",
                "APIs include operation over plot of land and its maintennance",
                "1.0",
                "Test",
                new Contact("Akash Verma", "address", "contact12345"),
                "Licence V1",
                "myLicensesite",
                Collections.emptyList()
        );
    }

}
