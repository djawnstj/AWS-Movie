package com.awsmovie.exception.nonExistent

import com.awsmovie.controller.handler.ErrorCode

/**
 * 존재하지 않는 영화 평점 예외 클래
 */
class NonExistentMovieRateException(override val errorCode: ErrorCode): NonExistentException(errorCode)