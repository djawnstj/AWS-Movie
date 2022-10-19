package com.awsmovie.controller

import com.awsmovie.entity.user.User
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @Test
    fun 회원가입_API() {
        // given
        val user = User.createUser("test", "123", "테스트 유저")

        val content: String = objectMapper.writeValueAsString(user)

        // when
        val actions = mvc.perform(
            post("/aws-movie-api/v1/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
//            .andExpect(content().string("유저 정보 저장 성공"))

        // then
        actions.andExpect(status().isCreated)

    }

    @Test
    fun 회원가입_API_중복ID_에러() {
        // given
        val user1 = User.createUser("test", "123", "테스트 유저")
        val user2 = User.createUser("test", "123", "테스트 유저2")

        val content1: String = objectMapper.writeValueAsString(user1)
        val content2: String = objectMapper.writeValueAsString(user2)

        // when
        mvc.perform(post("/aws-movie-api/v1/users")
            .content(content1)
            .contentType(MediaType.APPLICATION_JSON))

        val actions = mvc.perform(post("/aws-movie-api/v1/users")
            .content(content2)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))

        // then
        actions.andExpect(status().isConflict)

    }

    @Test
    fun 회원가입_API_HTTP_BODY_누락_에러() {
        // given

        // when
        val actions = mvc.perform(
            post("/aws-movie-api/v1/users")
        )

        // then
        actions.andExpect(status().isBadRequest)
    }

}