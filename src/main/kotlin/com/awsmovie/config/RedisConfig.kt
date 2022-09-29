package com.awsmovie.config

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer


@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800) // 세션 만료 시간: 1800초
@RequiredArgsConstructor
class RedisConfig(
    private val objectMapper: ObjectMapper
): AbstractHttpSessionApplicationInitializer() {

    @Value("\${spring.redis.host}")
    private lateinit var host: String

    @Value("\${spring.redis.port}")
    private var port: Int = 0

    @Value("\${spring.redis.password}")
    private lateinit var password: String

    @Bean
    fun lettuceConnectionFactory(): RedisConnectionFactory {
        val standaloneConfiguration = RedisStandaloneConfiguration(host, port)
        standaloneConfiguration.password =
            if (password.isEmpty()) RedisPassword.none() else RedisPassword.of(password)
        return LettuceConnectionFactory(standaloneConfiguration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(lettuceConnectionFactory())
        redisTemplate.setEnableTransactionSupport(true)
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        return redisTemplate
    }

}