package com.awsmovie.repository.genre

import com.awsmovie.entity.genre.Genre
import lombok.RequiredArgsConstructor
import javax.persistence.EntityManager
import javax.transaction.Transactional

@RequiredArgsConstructor
@Transactional
open class GenreRepositoryImpl(
    private val em: EntityManager
): GenreRepository {

    override fun save(genre: Genre): Long {
        em.persist(genre)
        return genre.genreId ?: -1
    }

}