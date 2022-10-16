package com.awsmovie.exception

/**
 * 존재하지 않는 점영화 예외 클래
 */
class NonexistentMovieException(override val message: String): NullPointerException(message) {
}