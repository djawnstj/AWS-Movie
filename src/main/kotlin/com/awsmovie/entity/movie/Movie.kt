package com.awsmovie.entity.movie

import com.awsmovie.entity.BaseEntity
import com.awsmovie.entity.movie.genre.MovieGenre
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.persistence.FetchType.LAZY as LAZY

@Entity
data class Movie protected constructor(
    val movieName: String,
    val runTime: Int,
    val openingDate: LocalDateTime,
    val summary: String,
//    @ManyToMany(mappedBy = "movies")
//    val genres: List<Genre> = ArrayList(),
    @OneToMany(mappedBy = "movieGenreMovie", cascade = [CascadeType.REMOVE])
    val genres: List<MovieGenre> = ArrayList(),
    @OneToOne(mappedBy = "movie", fetch = LAZY, cascade = [CascadeType.REMOVE, CascadeType.ALL])
    val movieImage: MovieImage,
    @OneToMany(mappedBy = "movie", cascade = [CascadeType.REMOVE])
    @JsonManagedReference
    val rates: List<MovieRate> = ArrayList(),
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    val movieId: Long? = null

    //============= 연관관계 메서드 =============//
    private fun setMovieImage(movieImage: MovieImage) {
        movieImage.setMovieItem(this)
    }

    companion object {

        //============= 생성 메서드 =============//
        fun createMovie(movieName: String, runTime: Int, openingDate: LocalDateTime, summary: String, movieImage: MovieImage, ): Movie {
            val movie = Movie(
                movieName = movieName,
                runTime = runTime,
                openingDate = openingDate,
                summary = summary,
                movieImage = movieImage
            )
            movie.setMovieImage(movieImage)
            return movie
        }

    }

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