package com.awsmovie.exception.nonExistent

import com.awsmovie.controller.handler.ErrorCode

/**
 * 존재하지 않는 회원 예외 클래
 */
class NonExistentUserException(override val errorCode: ErrorCode): NonExistentException(errorCode)