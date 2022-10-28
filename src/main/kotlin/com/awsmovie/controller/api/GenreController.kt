package com.awsmovie.controller.api

import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.entity.movie.genre.Genre
import com.awsmovie.entity.movie.genre.GenreCode
import com.awsmovie.service.genre.GenreService
import io.swagger.annotations.ApiOperation
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aws-movie-api/v1")
@RequiredArgsConstructor
class GenreController(
    private val genreService: GenreService
) {

    @GetMapping("/genres")
    @ApiOperation(value = "장르 목록 조회", notes = "전체 장르를 조회합니다.")
    fun genres(): BaseResponse {
        val genres = GenreCode.toList()

        return ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "장르 조회 성공",
            genres.size,
            genres
        )

    }

    @GetMapping("/genres/save")
    fun save(): BaseResponse {
        GenreCode.values().forEach {
            genreService.saveGenre(Genre.createGenre(it))
        }
        HttpStatus.OK.apply { return BaseResponse(value(), this, "ok") }
    }

}