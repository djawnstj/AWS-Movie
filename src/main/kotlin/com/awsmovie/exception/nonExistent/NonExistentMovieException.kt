package com.awsmovie.exception.nonExistent

import com.awsmovie.controller.handler.ErrorCode

/**
 * 존재하지 않는 점영화 예외 클래
 */
class NonExistentMovieException(override val errorCode: ErrorCode): NonExistentException(errorCode)