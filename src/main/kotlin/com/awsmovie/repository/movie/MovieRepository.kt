package com.awsmovie.repository.movie

import com.awsmovie.entity.movie.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {

    /**
     * 영화 정보 저장
     */
    fun save(movie: Movie): Movie

    /**
     * 영화 이름으로 검색
     */
    fun findByMovieName(movieName: String): List<Movie>

    /**
     * 개봉일 기준 최신순으로 영화 목록 조회
     */
    fun findAllByOrderByOpeningDateDesc(): List<Movie>

    /**
     * 영화 id로 검색
     */
    override fun findById(movieId: Long): Optional<Movie>

    /**
     * 전체 영화목록 조회
     */
    override fun findAll(): List<Movie>

}