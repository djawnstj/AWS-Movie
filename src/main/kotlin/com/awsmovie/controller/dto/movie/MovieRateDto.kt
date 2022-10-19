package com.awsmovie.controller.dto.movie

import com.awsmovie.controller.dto.user.UserDto

data class MovieRateDto(
    val user: UserDto,
    val rate: Int,
    val comment: String
)