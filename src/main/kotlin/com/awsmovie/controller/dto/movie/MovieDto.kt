package com.awsmovie.controller.dto.movie

import com.awsmovie.entity.movie.genre.GenreCode
import java.time.LocalDateTime

data class MovieDto(
    val movieName: String,
    val runTime: Int,
    val openingDate: LocalDateTime,
    val summary: String,
    val genres: List<GenreDto> = ArrayList(),
    val movieImagePath: String?,
    val rates: List<MovieRateDto> = ArrayList(),
)