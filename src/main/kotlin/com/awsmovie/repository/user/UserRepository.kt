package com.awsmovie.repository.user

import com.awsmovie.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, Long> {

    /**
     * 유저 정보 저장
     */
    fun save(user: User): User

    /**
     * ID로 유저 정보 조회
     */
    override fun findById(uid: Long): Optional<User>
//    fun findById(uid: Long): User

    /**
     * 유저 목록 조회
     */
    override fun findAll(): List<User>
//    fun findAll(): List<User>

    /**
     * 회원 아이디로 조회
     */
    fun findByUserId(userId: String): User?

    /**
     * 회원 아이디, 비밀번호로 조회
     */
    fun findByUserIdAndUserPw(userId: String, userPw: String): User?

}


