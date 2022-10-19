package com.awsmovie.controller.response

import lombok.Data
import org.springframework.http.HttpStatus

@Data
data class ListResponse(
    override val code: Int,
    override val status: HttpStatus,
    override val message: String,
    val count: Int,
    val result: List<Any>,
): BaseResponse(code, status, message)