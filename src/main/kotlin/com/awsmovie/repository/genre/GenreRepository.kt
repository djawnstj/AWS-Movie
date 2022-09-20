package com.awsmovie.repository.genre

import com.awsmovie.entity.genre.Genre
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository {

    /**
     * 장르 저장
     */
    fun save(genre: Genre): Long

}