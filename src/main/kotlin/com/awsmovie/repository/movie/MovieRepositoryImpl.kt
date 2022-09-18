package com.awsmovie.repository.movie

import com.awsmovie.entity.movie.Movie
import lombok.RequiredArgsConstructor
import javax.persistence.EntityManager
import javax.transaction.Transactional

@RequiredArgsConstructor
@Transactional
open class MovieRepositoryImpl(
    private val em: EntityManager
): MovieRepository {

    override fun save(movie: Movie): Long {
        em.persist(movie)
        return movie.movieId ?: -1
    }

    override fun findAllByOpeningDateDesc(): List<Movie> =
        em.createQuery("select m from Movie m order by m.openingDate desc ", Movie::class.java).resultList

    override fun findAllByRating(): List<Movie> = listOf()

    override fun findById(movieId: Long): Movie {
        TODO("Not yet implemented")
    }

    override fun findByName(movieName: String): Movie {
        TODO("Not yet implemented")
    }

}