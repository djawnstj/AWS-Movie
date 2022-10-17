package com.awsmovie.exception.nonExistent

import com.awsmovie.controller.handler.ErrorCode

/**
 * 존재하지 않는 값 예외 클래
 */
open class NonExistentException(open val errorCode: ErrorCode): NullPointerException(errorCode.message)