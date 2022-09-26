package com.awsmovie.service.user

import com.awsmovie.entity.user.User
import com.awsmovie.exception.UserDuplicateException
import com.awsmovie.repository.user.UserRepository

class UserService(
    private val userRepository: UserRepository,
) {

    /**
     * 회원 가입
     */
    fun join(user: User): User {
        validateDuplicateUser(user.userId)
        return userRepository.save(user)
    }

    /**
     * 아이디 중복 체크
     */
    fun validateDuplicateUser(userId: String) {
        check(userRepository.findByUserId(userId) == null) { throw UserDuplicateException("이미 존재하는 회원입니다.") }
    }

    /**
     * 로그인 검중
     */
    fun validateUser(userId: String, userPw: String): Boolean =
        userRepository.findByUserIdAndUserPw(userId, userPw) != null

}