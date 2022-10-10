package com.awsmovie.repository.genre

import com.awsmovie.entity.genre.Genre
import com.awsmovie.entity.genre.GenreCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository: JpaRepository<Genre, Long> {

    /**
     * 장르 저장
     */
    fun save(genre: Genre): Genre

    /**
     * 장르 조회
     */
    fun findByGenreIn(@Param("genre") genres: List<GenreCode>): List<Genre>

}