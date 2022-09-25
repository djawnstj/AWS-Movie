package com.awsmovie.repository.user

import com.awsmovie.entity.user.User
import lombok.RequiredArgsConstructor
import javax.persistence.EntityManager
import javax.transaction.Transactional

@RequiredArgsConstructor
@Transactional
open class UserRepositoryImpl(
    private val em: EntityManager
){

//    override fun save(user: User): Long {
//        em.persist(user)
//        return user.uid ?: -1
//    }
//
//    override fun findById(uid: Long): User = em.find(User::class.java, uid)
//
//    override fun findAll(): List<User> = em.createQuery("select u from User u", User::class.java).resultList

}