package com.awsmovie.repository

import com.awsmovie.entity.genre.Genre
import com.awsmovie.entity.genre.GenreCode
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
        val genre = Genre.createGenre(GenreCode.ROMANCE)

        // when
        val savedGenre = genreRepository.save(genre)

        // then
        assertEquals(genre.genreId, savedGenre)
    }

    @Test
    fun 장르_조회() {
        // given
        val genre1 = Genre.createGenre(GenreCode.ROMANCE)
        val genre2 = Genre.createGenre(GenreCode.ACTION)

        genreRepository.save(genre1)
        genreRepository.save(genre2)

        val genreList = listOf(genre1.genre, genre2.genre)

        // when
        val genres = genreRepository.findByGenreIn(genreList)

        // then
        genres.forEach { println(it.genreId) }

    }

}