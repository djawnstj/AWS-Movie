package com.awsmovie.service.movie

import com.awsmovie.entity.movie.Movie
import com.awsmovie.repository.movie.MovieRepository

class MovieService(
    private val movieRepository: MovieRepository
) {

    /**
     * 영화 저장
     */
    fun saveMovie(movie: Movie): Movie = movieRepository.save(movie)

}