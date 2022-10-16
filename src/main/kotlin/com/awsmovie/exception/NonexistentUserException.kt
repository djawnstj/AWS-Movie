package com.awsmovie.exception

/**
 * 존재하지 않는 회원 예외 클래
 */
class NonexistentUserException(override val message: String): NullPointerException(message) {
}