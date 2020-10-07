package com.aseprojali.app.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile({ApplicationConstant.SPRING_PROFILE_SWAGGER, ApplicationConstant.SPRING_PROFILE_DEVELOPMENT})
@EnableSwagger2
@Slf4j
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private final AppProperties appProperties;

    @Bean
    public Docket apiRegistration() {
        return createDocket("registration", "com.aseprojali.app.web.rest");
    }

    private Docket createDocket(String s, String s2) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(s)
                .select()
                .apis(RequestHandlerSelectors.basePackage(s2))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Registration")
                .description("APIs")
                .termsOfServiceUrl("https://app.aseprojali.com/tos")
                .contact(new Contact("Asep Rojali", "http://app.aseprojali.com", "aseprojali@gmail.com"))
                .license("license")
                .licenseUrl("https://www.aseprojali.com")
                .version("1.0.0")
                .build();
    }

}
