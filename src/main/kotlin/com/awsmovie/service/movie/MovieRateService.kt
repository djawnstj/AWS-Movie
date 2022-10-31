package com.awsmovie.service.movie

import com.awsmovie.controller.handler.ErrorCode
import com.awsmovie.entity.movie.Movie
import com.awsmovie.entity.movie.MovieRate
import com.awsmovie.exception.nonExistent.NonExistentMovieException
import com.awsmovie.exception.nonExistent.NonExistentUserException
import com.awsmovie.repository.MovieRateRepository
import com.awsmovie.repository.movie.MovieRepository
import com.awsmovie.repository.user.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class MovieRateService(
    private val movieRateRepository: MovieRateRepository,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
) {

    /**
     * 영화 평점 저장
     */
    @Transactional
    fun saveRate(uid: Long, movieId: Long, rate: Double, comment: String): MovieRate {

        check(rate in 1.0..5.0) { throw IllegalArgumentException("평점이 0점 미만이거나 5점을 초과하였습니다.") }

        val user = userRepository.findByIdOrNull(uid)
        val movie = movieRepository.findByIdOrNull(movieId)

        check (user != null) { throw NonExistentUserException(ErrorCode.USER_NOT_FOUND) }
        check(movie != null) { throw NonExistentMovieException(ErrorCode.MOVIE_NOT_FOUND) }

        val rate = MovieRate.create(user, movie, rate, comment)

        return movieRateRepository.save(rate)
    }

    /**
     * 페이징으로 영화 조회
     */
    fun findAllWithPaging(pageable: Pageable): Page<MovieRate> = movieRateRepository.findAll(pageable)

    /**
     * 영화별 최신순 4개 평점 조회
     */
    fun findTop4ByMovie(movie: Movie): List<MovieRate> = movieRateRepository.findTop4ByMovieOrderByUpdateTimeDesc(movie)

}