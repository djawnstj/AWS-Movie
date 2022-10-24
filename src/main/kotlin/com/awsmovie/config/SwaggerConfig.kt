package com.awsmovie.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    companion object {
        private const val API_NAME = "AWS Movie API"
        private const val API_VERSION = "0.0.1"
        private const val API_DESCRIPTION = "AWS Movie API 명세서"

        private const val AUTHOR = "Junseo Eom"
        private const val E_MAIL = "djawnstj44@gmail.com"
    }

    @Bean
    fun api(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.awsmovie.controller.api"))
            .paths(PathSelectors.any())
            .build()

    fun apiInfo(): ApiInfo =
        ApiInfoBuilder()
            .title(API_NAME)
            .version(API_VERSION)
            .description(API_DESCRIPTION)
            .contact(Contact(AUTHOR, null, E_MAIL))
            .build()

}