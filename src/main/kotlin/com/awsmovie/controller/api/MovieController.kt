package com.awsmovie.controller.api

import com.awsmovie.controller.dto.movie.GenreDto
import com.awsmovie.controller.dto.movie.MovieDto
import com.awsmovie.controller.dto.movie.MovieRateDto
import com.awsmovie.controller.dto.user.UserDto
import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.entity.movie.genre.GenreCode
import com.awsmovie.service.genre.GenreService
import com.awsmovie.service.movie.MovieImageService
import com.awsmovie.service.movie.MovieRateService
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
    private val movieImageService: MovieImageService,
    private val genreService: GenreService,
    private val movieRateService: MovieRateService,
) {


    @RequestMapping(value =["/movies/test"], method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
    fun test(@RequestPart(value = "genres") vararg genres: GenreCode): BaseResponse {
        genres.forEach { println(it) }

        return BaseResponse(HttpStatus.OK.value(), HttpStatus.OK, "ok")
    }

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

        val imageUrl = movieImageService.uploadToS3(multipartFile)

        movieDto.apply {

            movieService.saveMovie(movieName, runTime, openingDate, summary, genreCode.toList(), imageUrl)

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

        val movies = movieService.getMovieListByRating(pageable)

        val result = mutableListOf<MovieDto>()

        movies.forEach { movie ->

            val genres = mutableListOf<GenreDto>()
            val rates = mutableListOf<MovieRateDto>()

            movie.genres.forEach {
                it.movieGenreGenre.genre.apply {
                    genres += GenreDto(genreCode, genreKrName, genreEnName)
                }
            }

            movie.rates.forEach {
                rates += MovieRateDto(
                    UserDto(it.user.userName, it.user.userId, it.user.userPw),
                    it.rate,
                    it.comment
                )
            }

            result += MovieDto(
                movieName= movie.movieName,
                runTime = movie.runTime,
                openingDate = movie.openingDate,
                summary = movie.summary,
                genres = genres,
                movieImagePath = movie.movieImage.imagePath,
                rates = rates)
        }

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

        val result = mutableListOf<MovieDto>()

        val movie = movieService.findMovieInfo(movieId)

        movie?.apply {

            val genreList = mutableListOf<GenreDto>()
            genres.forEach {
                it.movieGenreGenre.genre.apply {
                    genreList += GenreDto(genreCode, genreKrName, genreEnName)
                }
            }

            val rateList = mutableListOf<MovieRateDto>()

            val movieDto = MovieDto(
                movieName = movieName,
                runTime = runTime,
                openingDate = openingDate,
                summary = summary,
                genres = genreList,
                movieImagePath = movieImage.imagePath,
                rates = rateList,
            )

            result += movieDto

        }

        return ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "영화 상세 정체 조회 성공",
            result.count(),
            result
        )

    }

}