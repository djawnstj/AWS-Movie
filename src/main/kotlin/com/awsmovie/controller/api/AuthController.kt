package com.awsmovie.controller.api

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
class AuthController {

    @GetMapping("/auth")
    @ResponseBody
    fun authTest(loginId: String, loginPw: String, session: HttpSession): String {

        session.setAttribute("session", loginId)
        val getSession = session.getAttribute("session")
        return getSession.toString()
    }

}