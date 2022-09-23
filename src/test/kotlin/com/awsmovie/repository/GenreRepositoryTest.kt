package com.awsmovie.repository

import com.awsmovie.entity.genre.Genre
import com.awsmovie.entity.genre.GenreEnum
import com.awsmovie.repository.genre.GenreRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class GenreRepositoryTest @Autowired constructor(
    private val genreRepository: GenreRepository
) {

    @Test
    fun 장르_저장() {
        // given
        val genre = Genre.createGenre(GenreEnum.ROMANCE)

        // when
        val savedGenre = genreRepository.save(genre)

        // then
        assertEquals(genre.genreId, savedGenre)
    }

}