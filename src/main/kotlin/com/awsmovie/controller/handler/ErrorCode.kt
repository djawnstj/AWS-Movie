package com.awsmovie.controller.handler

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터 값을 확인해주세요."),

    //404 NOT_FOUND 잘못된 리소스 접근
    DEFAULT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 정보입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원 ID 입니다."),
    MOVIE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 영화 ID 입니다."),
    RATE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 평점 ID 입니다."),

    //409 CONFLICT 중복된 리소스
    DUPLICATE_USER_ID(HttpStatus.CONFLICT, "존재하는 회원 ID 입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다. 서버 팀에 연락주세요!");

}