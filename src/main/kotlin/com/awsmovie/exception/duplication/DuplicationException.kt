package com.awsmovie.exception.duplication

import com.awsmovie.controller.handler.ErrorCode

/**
 * 중복 값 예외 클래스
 */
open class DuplicationException(open val errorCode: ErrorCode): IllegalStateException(errorCode.message)