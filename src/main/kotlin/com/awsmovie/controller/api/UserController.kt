package com.awsmovie.controller.api

import com.awsmovie.controller.dto.user.UserDto
import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.service.movie.MovieImageService
import com.awsmovie.service.user.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpSession

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws-movie-api/v1")
@EnableRedisHttpSession
class UserController(
    private val movieImageService: MovieImageService,
    private val userService: UserService
) {
    /**
     * 회원가입
     */
    @PostMapping("/users")
    fun create(@RequestBody userDto: UserDto): BaseResponse {

        userDto.apply {

            userService.join(userName, userId, userPw)

            return BaseResponse(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                "유저 정보 저장 성공",
            )

        }

    }

    /**
     * 로그인
     */
    @GetMapping("/users")
    fun findUser(userId: String, userPw: String, session: HttpSession): BaseResponse {

        userService.findUserByUserIdAndUserPw(userId, userPw).let {

            val result = if (it != null) listOf(it) else listOf()

            val res = ListResponse(
                code = HttpStatus.OK.value(),
                status = HttpStatus.OK,
                message = "유저 정보 조회 성공",
                count = result.size,
                result = result
            )

            it?.apply { session.setAttribute("uid", uid) }

            return res

        }

    }

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("images") multipartFile: MultipartFile): String = movieImageService.uploadToS3(multipartFile)

}