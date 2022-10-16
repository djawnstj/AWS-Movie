package com.awsmovie.service.user

import com.awsmovie.entity.user.User
import com.awsmovie.exception.UserValidationException
import com.awsmovie.repository.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    /**
     * 회원 가입
     */
    fun join(userName: String, userId: String, userPw: String): User {
        check(userName.isNotEmpty() && userId.isNotEmpty() && userPw.isNotEmpty()) { throw IllegalStateException("파라미터가 올바르지 않습니다.") }
        val user = User.createUser(userId, userPw, userName)
        validateDuplicateUser(user.userId)
        return userRepository.save(user)
    }

    /**
     * 아이디 중복 체크
     */
    fun validateDuplicateUser(userId: String) {
        check(userRepository.findByUserId(userId) == null) { throw UserValidationException("이미 존재하는 회원입니다.") }
    }

    /**
     * 로그인 검중
     */
    fun validateUser(userId: String, userPw: String): Boolean =
        userRepository.findByUserIdAndUserPw(userId, userPw) != null

    /**
     * userId, userPw 로 회원 정보 조회
     */
    fun findUserByUserIdAndUserPw(userId: String, userPw: String): User? =
        userRepository.findByUserIdAndUserPw(userId, userPw)

}