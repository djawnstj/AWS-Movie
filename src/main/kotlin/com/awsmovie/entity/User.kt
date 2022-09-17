package com.awsmovie.entity

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class User protected constructor(
    val userId: String,
    val userPw: String,
    val userName: String,
): BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val uid: Long? = null

    companion object {

        private const val TAG = "User"

        //============ 생성 메서드 ============//
        fun createUser(userId: String, userPw: String, userName: String):User = User(userId, userPw, userName)

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return uid != null && uid == other.uid
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(uid = $uid , createTime = $createTime , updateTime = $updateTime , userId = $userId , userPw = $userPw , userName = $userName )"
    }

}