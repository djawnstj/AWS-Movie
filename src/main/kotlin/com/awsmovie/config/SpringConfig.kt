package com.awsmovie.config

import com.awsmovie.repository.user.UserRepository
import com.awsmovie.repository.user.UserRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

@Configuration
class SpringConfig(
    val em: EntityManager
) {

    @Bean
    fun userRepository(): UserRepository = UserRepositoryImpl(em)

}