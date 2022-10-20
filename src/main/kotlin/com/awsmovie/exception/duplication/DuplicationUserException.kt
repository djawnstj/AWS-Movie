package com.awsmovie.exception.duplication

import com.awsmovie.controller.handler.ErrorCode

/**
 * 중복 회원 예외 클래스
 */
class DuplicationUserException(override val errorCode: ErrorCode): DuplicationException(errorCode)