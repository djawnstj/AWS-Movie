package com.awsmovie.entity

import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
data class MovieRate protected constructor(
    @OneToOne(fetch = LAZY) @JoinColumn(name = "uid")
    val user: User,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "movie_id")
    val movie: Movie,
    val rate: Int,
    val comment: String,
) : BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val movieRateId: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as MovieRate

        return movieRateId != null && movieRateId == other.movieRateId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(movieRateId = $movieRateId , createTime = $createTime , updateTime = $updateTime , rate = $rate , comment = $comment )"
    }

}