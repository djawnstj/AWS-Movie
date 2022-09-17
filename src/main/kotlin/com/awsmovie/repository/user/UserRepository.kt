package com.awsmovie.repository.user

import com.awsmovie.entity.user.User

interface UserRepository {

    /**
     * 유저 정보 저장
     */
    fun save(user: User): Long

    /**
     * ID로 유저 정보 조회
     */
    fun findById(uid: Long): User

    /**
     * 유저 목록 조회
     */
    fun findAll(): List<User>

}


