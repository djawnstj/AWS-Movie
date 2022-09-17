package com.awsmovie.repository

import com.awsmovie.entity.user.User
import com.awsmovie.repository.user.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository,
) {

    @Test
    fun 회원_저장() {
        // given
        val user = User.createUser("test", "123", "테스트 유저")

        // when
        val savedUid = userRepository.save(user)

        // then
        assertEquals(user.uid, savedUid)

    }

    @Test
    fun 회원_검색() {
        // given
        val user = User.createUser("test", "123", "테스트 유저")
        val savedUid = userRepository.save(user)

        // when
        val findUser = userRepository.findById(savedUid)

        // then
        assertEquals(savedUid, findUser.uid)

    }

}