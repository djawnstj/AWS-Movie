package com.awsmovie.service.movie

import com.awsmovie.entity.movie.Movie
import com.awsmovie.entity.movie.MovieImage
import com.awsmovie.entity.movie.genre.GenreCode
import com.awsmovie.entity.movie.genre.MovieGenre
import com.awsmovie.repository.genre.GenreRepository
import com.awsmovie.repository.movie.MovieRepository
import com.awsmovie.repository.movieGenre.MovieGenreRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class MovieService(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository,
    private val movieGenreRepository: MovieGenreRepository
) {

    /**
     * 영화 저장
     */
    @Transactional
    fun saveMovie(movieName: String, runTime: Int, openingDate: LocalDateTime, summary: String, genres: List<GenreCode>, imagePath: String): Movie {

        val genreList = genreRepository.findByGenreIn(genres)

        // 영화 이미지 객체 생성
        val movieImage = MovieImage.createMovieImage(imagePath)

        // 영화 객체 생성
        val movie = Movie.createMovie(movieName, runTime, openingDate, summary, movieImage)

        movieRepository.save(movie)

        genreList.forEach { genre ->
            movieGenreRepository.save(MovieGenre.create(genre, movie))
        }

        return movie
    }

    /**
     * 영화 평점순 조회
     */
    fun getMovieListByRating(pageable: Pageable): Page<Movie> = movieRepository.findAll(pageable)

    /**
     * 영화 ID로 영화 조회
     */
    fun findMovieInfo(movieId: Long): Movie? = movieRepository.findByIdOrNull(movieId)

}