package com.awsmovie.service.genre

import com.awsmovie.entity.movie.genre.Genre
import com.awsmovie.entity.movie.genre.GenreCode
import com.awsmovie.repository.genre.GenreRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GenreService(
    private val genreRepository: GenreRepository
) {

    /**
     * 장르 저장
     */
    @Transactional
    fun saveGenre(genre: Genre): Genre = genreRepository.save(genre)

    /**
     * 장르 조회
     */
    fun findGenres(genres: List<GenreCode>): List<Genre> = genreRepository.findByGenreIn(genres)

}