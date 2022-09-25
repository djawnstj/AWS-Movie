package com.awsmovie.repository.movie

import com.awsmovie.entity.movie.Movie
import lombok.RequiredArgsConstructor
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@RequiredArgsConstructor
@Transactional
open class MovieRepositoryImpl(
    private val em: EntityManager
) {

//    override fun save(movie: Movie): Long {
//        em.persist(movie)
//        return movie.movieId ?: -1
//    }
//
//    override fun findByMovieName(movieName: String): List<Movie> =
//        em.createQuery("select m from Movie m where m.movieName = :movieName", Movie::class.java)
//            .setParameter("movieName", movieName)
//            .resultList
//
//    override fun findAllByOpeningDateDesc(): List<Movie> =
//        em.createQuery("select m from Movie m order by m.openingDate desc", Movie::class.java)
//            .resultList
//
//    override fun findById(movieId: Long): Movie = em.find(Movie::class.java, movieId)
//
//    override fun findAll(): List<Movie> = em.createQuery("select m from Movie m", Movie::class.java).resultList

}