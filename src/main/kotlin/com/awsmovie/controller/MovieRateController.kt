package com.awsmovie.controller

import com.awsmovie.controller.dto.movie.MovieRateDto
import com.awsmovie.controller.dto.user.UserDto
import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.service.movie.MovieRateService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws-movie-api/v1")
class MovieRateController(
    private val movieRateService: MovieRateService
) {

    @PostMapping("/movie-rates")
    fun create(
        @RequestParam uid: Long,
        @RequestParam movieId: Long,
        @RequestParam rate: Int,
        @RequestParam comment: String
    ): ResponseEntity<BaseResponse> {

        val movieRate = movieRateService.saveRate(uid, movieId, rate, comment)

        movieRate.apply {

            val userDto = UserDto(user.userName, user.userId,)

            val movieRateDto = MovieRateDto(userDto, rate, comment)

            val res = ListResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "영화 평점 저장 성공",
                1,
                listOf(movieRateDto)
            )

            return ResponseEntity(res, res.status)

        }
    }

    @GetMapping("/movie-rates")
    fun findRateList(@PageableDefault(size=10, sort=["createTime"], direction = Sort.Direction.DESC) pageable: Pageable): ResponseEntity<BaseResponse> {

        val rateList = movieRateService.findAllWithPaging(pageable)

        val res = ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "평점 리스트 조회 성공",
            rateList.size,
            rateList.toList()
        )

        return ResponseEntity(res, res.status)

    }

}