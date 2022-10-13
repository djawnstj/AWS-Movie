package com.awsmovie.repository.movieGenre

import com.awsmovie.entity.movie.genre.MovieGenre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieGenreRepository: JpaRepository<MovieGenre, Long> {

    /**
     * 영화, 장르 다대다 연관관계 저장
     */
    fun save(movieGenre: MovieGenre): MovieGenre

    /**
     * 모든 영화, 장르 연관관계 조회
     */
    override fun findAll(): List<MovieGenre>

}