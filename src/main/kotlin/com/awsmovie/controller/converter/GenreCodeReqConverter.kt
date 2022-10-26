package com.awsmovie.controller.converter

import com.awsmovie.entity.movie.genre.GenreCode
import org.springframework.core.convert.converter.Converter

class GenreCodeReqConverter: Converter<String, GenreCode> {

    override fun convert(genreCode: String): GenreCode = GenreCode.of(genreCode.toInt())

}