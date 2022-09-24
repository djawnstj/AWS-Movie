package com.awsmovie.repository.movie

import com.awsmovie.entity.movie.Movie
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MovieRepository {

    /**
     * 영화 정보 저장
     */
    fun save(movie: Movie): Long

    /**
     * 영화 이름으로 검색
     */
    fun findByMovieName(movieName: String): List<Movie>

    /**
     * 개봉일 기준 최신순으로 영화 목록 조회
     */
    fun findAllByOpeningDateDesc(): List<Movie>

    /**
     * 영화 id로 검색
     */
    fun findById(movieId: Long): Movie

    /**
     * 전체 영화목록 조회
     */
    fun findAll(): List<Movie>

}