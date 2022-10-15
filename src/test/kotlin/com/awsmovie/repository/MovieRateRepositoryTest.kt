package com.awsmovie.repository

import com.awsmovie.entity.movie.Movie
import com.awsmovie.entity.movie.MovieImage
import com.awsmovie.entity.movie.MovieRate
import com.awsmovie.entity.movie.genre.Genre
import com.awsmovie.entity.movie.genre.GenreCode
import com.awsmovie.entity.user.User
import com.awsmovie.repository.movie.MovieRepository
import com.awsmovie.repository.user.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class MovieRateRepositoryTest @Autowired constructor(
    private val movieRateRepository: MovieRateRepository,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository
) {

    @Test
    fun 영화_평점_저장() {
        // given
        val user = createUser("123")
        userRepository.save(user)
        val movie = createMovie("test1")
        movieRepository.save(movie)

        val rate = MovieRate.create(user, movie, 5, "테스트 코멘트")

        // when
        val savedRate = movieRateRepository.save(rate)

        // then
        assertEquals(rate.movieRateId, savedRate.movieRateId)

    }

    @Test
    fun 영화별_평점_조회() {
        // given
        val user1 = createUser("1")
        val user2 = createUser("2")
        userRepository.save(user1)
        userRepository.save(user2)
        val movie = createMovie("test1")
        movieRepository.save(movie)

        val rate1 = MovieRate.create(user1, movie, 5, "테스트 코멘트1")
        val rate2 = MovieRate.create(user2, movie, 5, "테스트 코멘트2")
        val rate3 = MovieRate.create(user1, movie, 5, "테스트 코멘트3")

        val savedRate1 = movieRateRepository.save(rate1)
        val savedRate2 = movieRateRepository.save(rate2)
        val savedRate3 = movieRateRepository.save(rate3)

        // when
        val page = PageRequest.of(0, 1)
        val movieRates = movieRateRepository.findAllByMovieOrderByUpdateTimeDesc(movie, page)

        // then
        movieRates.forEachIndexed { index, rate ->
            println("$index 번째 평점 = ${rate.user.userName}: ${rate.movie.movieName}, ${rate.comment}(${rate.rate})")
        }

    }

    @Test
    fun 영화별_평점_4개_조회() {
        // given
        val user1 = createUser("1")
        val user2 = createUser("2")
        userRepository.save(user1)
        userRepository.save(user2)
        val movie = createMovie("test1")
        movieRepository.save(movie)

        val rate1 = MovieRate.create(user1, movie, 5, "테스트 코멘트1")
        val rate2 = MovieRate.create(user2, movie, 5, "테스트 코멘트2")
        val rate3 = MovieRate.create(user1, movie, 5, "테스트 코멘트3")
        val rate4 = MovieRate.create(user1, movie, 5, "테스트 코멘트4")
        val rate5 = MovieRate.create(user1, movie, 5, "테스트 코멘트5")
        val rate6 = MovieRate.create(user2, movie, 5, "테스트 코멘트6")
        val rate7 = MovieRate.create(user1, movie, 5, "테스트 코멘트7")
        val rate8 = MovieRate.create(user1, movie, 5, "테스트 코멘트8")

        movieRateRepository.save(rate1)
        movieRateRepository.save(rate2)
        movieRateRepository.save(rate3)
        movieRateRepository.save(rate4)
        movieRateRepository.save(rate5)
        movieRateRepository.save(rate6)
        movieRateRepository.save(rate7)
        movieRateRepository.save(rate8)

        // when
        val movieRates = movieRateRepository.findTop4ByMovieOrderByUpdateTimeDesc(movie)

        // then
        movieRates.forEachIndexed { index, rate ->
            println("$index 번째 평점 = ${rate.user.userName}: ${rate.movie.movieName}, ${rate.comment}(${rate.rate})")
        }

    }

    private fun createUser(str: String) = User.createUser("test$str", "id$str", "테스트 유저$str")

    private fun createMovie(name: String): Movie {
        val genre = Genre.createGenre(GenreCode.ROMANCE)
        val movieImage = MovieImage.createMovieImage("test")
        return Movie.createMovie(name, 160, LocalDateTime.now(), "summary", movieImage)
    }


}