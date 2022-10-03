package com.awsmovie.repository

import com.awsmovie.entity.genre.Genre
import com.awsmovie.entity.genre.GenreEnum
import com.awsmovie.entity.movie.Movie
import com.awsmovie.entity.movie.MovieImage
import com.awsmovie.repository.movie.MovieRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class MovieRepositoryTest @Autowired constructor(
    private val movieRepository: MovieRepository
) {

    @Test
    fun 영화_저장() {
        // given
        val createMovie = createMovie()

        // when
        val savedMovie = movieRepository.save(createMovie)

        // then
        assertEquals(createMovie.movieId, savedMovie.movieId)

    }

    @Test
    fun 개봉일_역순_목록_조회() {
        // given
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.KOREAN)
        val movie1 = createMovie(LocalDateTime.parse("20220910000000", formatter))
        val movie2 = createMovie(LocalDateTime.parse("20220920000000", formatter))
        val movie3 = createMovie(LocalDateTime.parse("20220930000000", formatter))
        movieRepository.save(movie1)
        movieRepository.save(movie2)
        movieRepository.save(movie3)

        // when
        movieRepository.findAllByOrderByOpeningDateDesc()

        // then

    }

    @Test
    fun 영화_이름으로_조회() {
        // given
        val movie1 = createMovie("영화1")
        val movie2 = createMovie("영화2")
        val movie3 = createMovie("영화3")

        movieRepository.save(movie1)
        movieRepository.save(movie2)
        movieRepository.save(movie3)

        // when
        val foundMovies = movieRepository.findByMovieName(movie1.movieName)

        // then
        assertEquals(movie1.movieId, foundMovies[0].movieId)
    }

    private fun createMovie(): Movie {
        val genre = Genre.createGenre(GenreEnum.ROMANCE)
        val movieImage = MovieImage.createMovieImage("test")
        return Movie.createMovie("영화 1", 160, LocalDateTime.now(), "summary", listOf(genre), movieImage)
    }

    private fun createMovie(openingDate: LocalDateTime): Movie {
        val genre = Genre.createGenre(GenreEnum.ROMANCE)
        val movieImage = MovieImage.createMovieImage("test")
        return Movie.createMovie("영화 1", 160, openingDate, "summary", listOf(genre), movieImage)
    }

    private fun createMovie(name: String): Movie {
        val genre = Genre.createGenre(GenreEnum.ROMANCE)
        val movieImage = MovieImage.createMovieImage("test")
        return Movie.createMovie(name, 160, LocalDateTime.now(), "summary", listOf(genre), movieImage)
    }

}