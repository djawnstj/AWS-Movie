package com.awsmovie.controller.dto.movie

import com.awsmovie.controller.dto.user.UserDto

data class MovieRateDto(
    val movieRateId: Long?,
    val user: UserDto?,
    val movieId: Long,
    val rate: Double,
    val comment: String
)