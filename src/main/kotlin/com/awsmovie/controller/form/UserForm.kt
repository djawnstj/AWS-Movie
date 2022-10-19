package com.awsmovie.controller.form

import javax.validation.constraints.NotEmpty

data class UserForm(
    @field: NotEmpty(message = "회원 이름은 필수 입니다.")
    var userName: String = "",
    @field: NotEmpty(message = "ID 값은 필수 입니다.")
    var userId: String = "",
    @field: NotEmpty(message = "PW 값은 필수 입니다.")
    var userPw: String = "",
)