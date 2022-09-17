package com.awsmovie.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_time" , nullable = false, updatable = false,  columnDefinition = "DATE")
    var createTime: LocalDateTime? = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column(name = "updated_time", columnDefinition = "DATE")
    var updateTime  : LocalDateTime = LocalDateTime.now()
        protected set

}