package com.awsmovie.controller.api

import com.awsmovie.controller.dto.movie.MovieDto
import com.awsmovie.controller.dto.movie.MovieRateDto
import com.awsmovie.controller.dto.user.UserDto
import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.entity.movie.genre.Genre
import com.awsmovie.entity.movie.genre.GenreCode
import com.awsmovie.entity.movie.Movie
import com.awsmovie.entity.movie.MovieImage
import com.awsmovie.entity.movie.genre.MovieGenre
import com.awsmovie.repository.movie.MovieRepository
import com.awsmovie.repository.movieGenre.MovieGenreRepository
import com.awsmovie.service.genre.GenreService
import com.awsmovie.service.movie.MovieImageService
import com.awsmovie.service.movie.MovieRateService
import com.awsmovie.service.movie.MovieService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws-movie-api/v1")
class MovieController(
    private val movieService: MovieService,
    private val movieImageService: MovieImageService,
    private val genreService: GenreService,
    private val movieRateService: MovieRateService,
    private val movieGenreRepository: MovieGenreRepository,
    private val movieRepository: MovieRepository,
) {

    /**
     * 영화 저장
     */
    @PostMapping("/movies")
    fun create(
        @RequestBody   movieDto: MovieDto,
        @RequestParam movieName: String,
        @RequestParam runTime: String,
        @RequestParam openingDate: String,
        @RequestParam summary: String,
//        @RequestParam("image") multipartFile: MultipartFile,
        @RequestParam vararg genreCode: GenreCode
    ): ResponseEntity<BaseResponse> {

//        println("-=========================")
//        println("$movieName, $runTime, $openingDate, $summary,")
////        println("$movieName, $runTime, $openingDate, $summary, $genreCode")
//        println("-=========================")

        val imageUrl = movieImageService.uploadToS3(multipartFile)

        movieDto.apply {

            movieService.saveMovie(movieName, runTime, openingDate, summary, genreCode.toList(), imageUrl)

            val res = BaseResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "영화 정보 저장 성공"
            )

            return ResponseEntity(res, res.status)

        }
//
//        val res = BaseResponse(
//            HttpStatus.OK.value(),
//            HttpStatus.OK,
//            "영화 정보 저장 성공"
//        )
//
//        return ResponseEntity(res, res.status)

    }

    /**
     * 영화 평점순 조회
     * page : 요청할 페이지 번호
     * size : 한 페이지 당 조회 할 갯수 (default : 20)
     * sort : Sorting 에 대한 값 설정하는 파라미터로, 기본적으로 오름차순이다. 표기는 정렬한 필드명,정렬기준 ex) createdDate,desc
     */
    @GetMapping("/movies")
    fun findMovies(@PageableDefault(size=10, sort=["openingDate"], direction = Sort.Direction.DESC) pageable: Pageable): ResponseEntity<BaseResponse> {

        val movies = movieService.getMovieListByRating(pageable)

        val result = mutableListOf<MovieDto>()

        movies.forEach { movie ->

            println("genre : ${movie.genres.size}")

            val genres = mutableListOf<GenreCode>()
            val rates = mutableListOf<MovieRateDto>()

            movie.genres.forEach {
                genres += it.movieGenreGenre.genre
            }

            movie.rates.forEach {
                rates += MovieRateDto(
                    UserDto(it.user.userName, it.user.userId, it.user.userPw),
                    it.rate,
                    it.comment
                )
            }

            result += MovieDto(movie.movieName, movie.runTime, movie.openingDate, movie.summary, genres, movie.movieImage.imagePath, rates)
        }

        val res = ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "영화 목록 조회 성공",
            result.count(),
            result
        )

        return ResponseEntity(res, res.status)
    }

    /**
     * 영화 상세정보 조회
     */
    @GetMapping("/movies/{id}")
    fun movieInfo(@PathVariable("id") movieId: Long): ResponseEntity<BaseResponse> {

        val result = mutableListOf<MovieDto>()

        val movie = movieService.findMovieInfo(movieId)

        movie?.apply {

            val genreList = mutableListOf<GenreCode>()
            genres.forEach { genreList += it.movieGenreGenre.genre }

            val rateList = mutableListOf<MovieRateDto>()

            val movieDto = MovieDto(
                movieName,
                runTime,
                openingDate,
                summary,
                genreList,
                movieImage.imagePath,
                rateList,
            )

            result += movieDto

        }

        val res = ListResponse(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "영화 상세 정체 조회 성공",
            result.count(),
            result
        )

        return ResponseEntity(res, res.status)

    }

    @PostMapping("/movies/save")
    fun test12() {
        val genre = Genre.createGenre(GenreCode.ROMANCE)
        genreService.saveGenre(genre)

        for (i in 0..10) {
            val movie = createMovie("test $i")
            movieRepository.save(movie)

            movieGenreRepository.save(MovieGenre.create(genre, movie))
        }

    }

    @GetMapping("/test")
    fun test(@RequestParam vararg genreCode: GenreCode): String {
        var test = ""
        genreCode.forEach { test += it.genreKrName }
        return test
    }

    private fun createMovie(name: String): Movie {
        val movieImage = MovieImage.createMovieImage("test")
        return Movie.createMovie(name, 160, LocalDateTime.now(), "summary", movieImage)
    }

}