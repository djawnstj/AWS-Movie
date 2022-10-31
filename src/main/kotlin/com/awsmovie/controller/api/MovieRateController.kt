package com.awsmovie.controller.api

import com.awsmovie.controller.dto.movie.MovieRateDto
import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.service.movie.MovieRateService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws-movie-api/v1")
@EnableRedisHttpSession
class MovieRateController(
    private val movieRateService: MovieRateService
) {

    @PostMapping("/movie-rates")
    fun create(
        @RequestBody movieRateDto: MovieRateDto,
        session: HttpSession
    ): BaseResponse {

        val movieRate = movieRateService.saveRate(movieRateDto.user?.uid.toString().toLong(), movieRateDto.movieId, movieRateDto.rate, movieRateDto.comment)

        movieRate.apply {

            return ListResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "영화 평점 저장 성공",
                1,
                listOf(movieRateDto)
            )

        }
    }

    @GetMapping("/movie-rates")
    fun findRateList(@PageableDefault(size=10, sort=["createTime"], direction = Sort.Direction.DESC) pageable: Pageable): BaseResponse {

        val rateList = movieRateService.findAllWithPaging(pageable)

        return ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "평점 리스트 조회 성공",
            rateList.size,
            rateList.toList()
        )

    }

}