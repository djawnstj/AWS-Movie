package com.awsmovie.exception

/**
 * 중복 회원 예외 클래스
 */
class DuplicationUserException(message: String): IllegalStateException(message)