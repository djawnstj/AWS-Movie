package com.awsmovie.entity

import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.FetchType.LAZY as LAZY

@Entity
data class MovieImage protected constructor(
    var imagePath: String,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "movie_id")
    var movie: Movie,
): BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_image_id")
    val movieImageId: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as MovieImage

        return movieImageId != null && movieImageId == other.movieImageId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(movieImageId = $movieImageId , createTime = $createTime , updateTime = $updateTime , imagePath = $imagePath )"
    }

}