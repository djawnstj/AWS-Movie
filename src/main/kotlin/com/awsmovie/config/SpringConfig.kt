package com.awsmovie.config

import com.awsmovie.repository.genre.GenreRepository
import com.awsmovie.repository.genre.GenreRepositoryImpl
import com.awsmovie.repository.movie.MovieRepository
import com.awsmovie.repository.movie.MovieRepositoryImpl
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

    @Bean
    fun genreRepository(): GenreRepository = GenreRepositoryImpl(em)

    @Bean
    fun movieRepository(): MovieRepository = MovieRepositoryImpl(em)

}