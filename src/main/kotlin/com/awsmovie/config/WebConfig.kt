package com.awsmovie.config

import com.amazonaws.HttpMethod
import com.awsmovie.controller.converter.GenreCodeReqConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    @Value("\${spring.was.protocol}")
    private lateinit var protocol: String

    @Value("\${spring.was.host}")
    private lateinit var host: String

    @Value("\${spring.was.port}")
    private var port: Int = 0

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(GenreCodeReqConverter())
    }

    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/aws-movie-api/v1/**")
//            .allowedOrigins("$protocol://$host:$port")
//            .allowedMethods("*")
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8081")
            .allowedMethods(
                HttpMethod.GET.name,
                HttpMethod.POST.name,
                HttpMethod.PUT.name,
                HttpMethod.DELETE.name
            )
    }

}