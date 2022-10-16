package com.awsmovie.exception

/**
 * 존재하지 않는 영화 평점 예외 클래
 */
class NonexistentMovieRateException(override val message: String): NullPointerException(message) {
}