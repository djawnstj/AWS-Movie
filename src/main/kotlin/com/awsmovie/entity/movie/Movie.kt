package com.awsmovie.entity.movie

import com.awsmovie.entity.BaseEntity
import com.awsmovie.entity.genre.Genre
import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.LAZY as LAZY

@Entity
data class Movie protected constructor(
    val movieName: String,
    val runTime: Int,
    val openingDate: LocalDateTime,
    val summary: String,
    @OneToMany @JoinColumn(name = "genre_id")
    val genres: List<Genre> = ArrayList(),
    @OneToOne(mappedBy = "movieImageId", fetch = LAZY)
    val movieImage: MovieImage,
    @OneToMany(mappedBy = "movieRateId", fetch = LAZY)
    val rates: List<MovieRate>,
): BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    val movieId: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Movie

        return movieId != null && movieId == other.movieId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(movieId = $movieId , createTime = $createTime , updateTime = $updateTime , movieName = $movieName , runTime = $runTime , openingDate = $openingDate , summary = $summary )"
    }

}