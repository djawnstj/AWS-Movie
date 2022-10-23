package com.awsmovie.controller.api

import com.awsmovie.controller.dto.user.UserDto
import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.controller.response.ListResponse
import com.awsmovie.service.movie.MovieImageService
import com.awsmovie.service.user.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpSession

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws-movie-api/v1")
class UserController(
    private val movieImageService: MovieImageService,
    private val userService: UserService
) {
    /**
     * 회원가입
     */
    @PostMapping("/users")
    fun create(@RequestBody userDto: UserDto): ResponseEntity<BaseResponse> {

        userDto.apply {

            val savedUser = userService.join(userName, userId, userPw)

            val res = if (savedUser.userId == userDto.userId) {
                ListResponse(
                    code = HttpStatus.CREATED.value(),
                    status = HttpStatus.CREATED,
                    message = "유저 정보 저장 성공",
                    count = 1,
                    result = listOf(savedUser),
                )
            } else {
                ListResponse(
                    code = HttpStatus.BAD_REQUEST.value(),
                    status = HttpStatus.BAD_REQUEST,
                    message = "유저 정보 저장 실패",
                    count = 0,
                    result = listOf(),
                )
            }

            return ResponseEntity(res, res.status)

        }

    }

    /**
     * 로그인
     */
    @GetMapping("/users")
    fun findUser(userId: String, userPw: String, session: HttpSession): ResponseEntity<BaseResponse> {

        userService.findUserByUserIdAndUserPw(userId, userPw).let {

            val result = if (it != null) listOf(it) else listOf()

            val res = ListResponse(
                code = HttpStatus.OK.value(),
                status = HttpStatus.OK,
                message = "유저 정보 조회 성공",
                count = result.size,
                result = result
            )

            it?.apply { session.setAttribute("session", uid) }

            return ResponseEntity(res, res.status)

        }

    }

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("images") multipartFile: MultipartFile): String = movieImageService.uploadToS3(multipartFile)

}