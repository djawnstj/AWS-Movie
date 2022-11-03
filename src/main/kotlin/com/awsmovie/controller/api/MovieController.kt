package com.awsmovie.controller.api

import com.awsmovie.controller.dto.movie.MovieDto
import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.entity.movie.genre.GenreCode
import com.awsmovie.service.movie.MovieService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws-movie-api/v1")
class MovieController(
    private val movieService: MovieService,
) {

    /**
     * 영화 저장
     */
    @RequestMapping(value =["/movies"], method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
    fun create(
        @RequestPart(value = "movieDto") movieDto: MovieDto,
        @RequestPart(value = "image") multipartFile: MultipartFile,
        @RequestParam vararg genreCode: GenreCode
    ): BaseResponse {

        movieDto.apply {

            movieService.saveMovie(movieName, runTime, openingDate, summary, genreCode.toList(), multipartFile)

            return BaseResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "영화 정보 저장 성공"
            )

        }

    }

    /**
     * 영화 평점순 조회
     * page : 요청할 페이지 번호
     * size : 한 페이지 당 조회 할 갯수 (default : 20)
     * sort : Sorting 에 대한 값 설정하는 파라미터로, 기본적으로 오름차순이다. 표기는 정렬한 필드명,정렬기준 ex) createdDate,desc
     */
    @GetMapping("/movies")
    fun findMovies(@PageableDefault(size=10, sort=["openingDate"], direction = Sort.Direction.DESC) pageable: Pageable): BaseResponse {

        val result = movieService.getMovieListByRating(pageable)

        return ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "영화 목록 조회 성공",
            result.count(),
            result
        )

    }

    /**
     * 영화 상세정보 조회
     */
    @GetMapping("/movies/{id}")
    fun movieInfo(@PathVariable("id") movieId: Long): BaseResponse {

        val movie = movieService.findMovieInfo(movieId)

        val result = mutableListOf<MovieDto>()
        result += movie

        return ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "영화 상세 정체 조회 성공",
            result.count(),
            result
        )

    }

}