package com.awsmovie.entity.movie

import com.awsmovie.entity.BaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.FetchType.LAZY as LAZY

@Entity
data class MovieImage protected constructor(
    val imagePath: String,
): BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_image_id")
    val movieImageId: Long? = null

    @OneToOne(fetch = LAZY) @JoinColumn(name = "movie_id")
    @JsonBackReference
    lateinit var movie: Movie
        protected set

    fun setMovieItem(movie: Movie) {
        this.movie = movie
    }

    companion object {

        //============= 생성 메서드 =============//
        fun createMovieImage(imagePath: String): MovieImage = MovieImage(imagePath)

    }

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