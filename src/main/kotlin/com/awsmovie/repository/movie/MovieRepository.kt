package com.awsmovie.repository.movie

import com.awsmovie.entity.movie.Movie

interface MovieRepository {

    /**
     * 영화 정보 저장
     */
    fun save(movie: Movie): Long

    /**
     * 개봉일 기준 최신순으로 영화 목록 조회
     */
    fun findAllByOpeningDateDesc(): List<Movie>

    /**
     * 평점순 영화 목록 조회
     */
    fun findAllByRating(): List<Movie>

    /**
     * 영화 id로 검색
     */
    fun findById(movieId: Long): Movie

    /**
     * 영화 이름으로 검색
     */
    fun findByName(movieName: String): Movie

}