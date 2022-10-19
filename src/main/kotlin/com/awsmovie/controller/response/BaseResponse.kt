package com.awsmovie.controller.response

import lombok.Data
import org.springframework.http.HttpStatus

@Data
open class BaseResponse(
    open val code: Int,
    open val status: HttpStatus,
    open val message: String,
)