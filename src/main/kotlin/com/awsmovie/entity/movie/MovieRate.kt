package com.awsmovie.entity.movie

import com.awsmovie.entity.BaseEntity
import com.awsmovie.entity.user.User
import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
data class MovieRate protected constructor(
    @OneToOne(fetch = LAZY) @JoinColumn(name = "uid")
    val user: User,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "movie_id")
    @JsonBackReference
    val movie: Movie,
    val rate: Double,
    val comment: String,
) : BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_rate_id")
    val movieRateId: Long? = null

    companion object {

        //============= 생성 메서드 =============//
        fun create(user: User, movie: Movie, rate: Double, comment: String): MovieRate =
            MovieRate(user, movie, rate, comment)

    }

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