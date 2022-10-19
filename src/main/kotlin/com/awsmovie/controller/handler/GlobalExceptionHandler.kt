package com.awsmovie.controller.handler

import com.awsmovie.controller.response.BaseResponse
import com.awsmovie.exception.duplication.DuplicationException
import com.awsmovie.exception.nonExistent.NonExistentException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 존재하지 않는 정보 Handler
     */
    @ExceptionHandler(NonExistentException::class)
    protected fun handleNonExistentException(ex: NonExistentException): ResponseEntity<BaseResponse> {

        val res = BaseResponse(
            ex.errorCode.status.value(),
            ex.errorCode.status,
            ex.message ?: "존재하지 않는 정보입니다."
        )

        return ResponseEntity(res, res.status)
    }

    /**
     * 중복 값 Handler
     */
    @ExceptionHandler(DuplicationException::class)
    protected fun handleDuplicationException(ex: DuplicationException): ResponseEntity<BaseResponse> {

        val res = BaseResponse(
            ex.errorCode.status.value(),
            ex.errorCode.status,
            ex.message ?: "존재하지 않는 정보입니다."
        )

        return ResponseEntity(res, res.status)
    }

    /**
     * request parameter 누락 handler
     */
    @ExceptionHandler(MissingServletRequestParameterException::class)
    protected fun handleMissingServletRequestParameterException(ex: MissingServletRequestParameterException): ResponseEntity<BaseResponse> {

        val res = BaseResponse(
            ErrorCode.INVALID_PARAMETER.status.value(),
            ErrorCode.INVALID_PARAMETER.status,
            ErrorCode.INVALID_PARAMETER.message
        )

        return ResponseEntity(res, res.status)
    }

    /**
     * http body 누락 handler
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<BaseResponse> {

        val res = BaseResponse(
            ErrorCode.INVALID_PARAMETER.status.value(),
            ErrorCode.INVALID_PARAMETER.status,
            ErrorCode.INVALID_PARAMETER.message
        )

        return ResponseEntity(res, res.status)
    }

    /**
     * IllegalArgumentException Handler
     */
    @ExceptionHandler(IllegalArgumentException::class)
    protected fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<BaseResponse> {

        val res = BaseResponse(
            ErrorCode.INVALID_PARAMETER.status.value(),
            ErrorCode.INVALID_PARAMETER.status,
            ErrorCode.INVALID_PARAMETER.message
        )

        return ResponseEntity(res, res.status)
    }

    /**
     * NullPointerException Handler
     */
    @ExceptionHandler(NullPointerException::class)
    protected fun handleNullPointerException(ex: NullPointerException): ResponseEntity<BaseResponse> {

        val res = BaseResponse(
            ErrorCode.DEFAULT_NOT_FOUND.status.value(),
            ErrorCode.DEFAULT_NOT_FOUND.status,
            ErrorCode.DEFAULT_NOT_FOUND.message
        )

        return ResponseEntity(res, res.status)
    }

    /**
     * default Exception Handler
     */
    @ExceptionHandler(Exception::class)
    protected fun handleException(ex: Exception): ResponseEntity<BaseResponse> {

        val res = BaseResponse(
            ErrorCode.INTERNAL_SERVER_ERROR.status.value(),
            ErrorCode.INTERNAL_SERVER_ERROR.status,
            ErrorCode.INTERNAL_SERVER_ERROR.message
        )

        return ResponseEntity(res, res.status)
    }

}