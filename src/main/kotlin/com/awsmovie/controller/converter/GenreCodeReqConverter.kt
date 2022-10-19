package com.awsmovie.controller.converter

import com.awsmovie.entity.movie.genre.GenreCode
import org.springframework.core.convert.converter.Converter

class GenreCodeReqConverter: Converter<Int, GenreCode> {

    override fun convert(genreCode: Int): GenreCode = GenreCode.of(genreCode)

}