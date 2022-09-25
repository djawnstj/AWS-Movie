package com.awsmovie.repository.genre

import com.awsmovie.entity.genre.Genre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GenreRepository: JpaRepository<Genre, Long> {

    /**
     * 장르 저장
     */
    fun save(genre: Genre): Genre

}