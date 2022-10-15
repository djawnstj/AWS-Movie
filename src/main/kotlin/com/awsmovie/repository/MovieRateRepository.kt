package com.awsmovie.repository

import com.awsmovie.entity.movie.Movie
import com.awsmovie.entity.movie.MovieRate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRateRepository: JpaRepository<MovieRate, Long> {

    /**
     * 영화 평점 저장
     */
    fun save(movieRate: MovieRate): MovieRate

    /**
     * 영화별 평점 페이징 조회
     */
    fun findAllByMovieOrderByUpdateTimeDesc(movie: Movie, pageable: Pageable): Page<MovieRate>

    /**
     * 영화별 최신순 평점 4개 조회
     */
    fun findTop4ByMovieOrderByUpdateTimeDesc(movie: Movie): List<MovieRate>

}