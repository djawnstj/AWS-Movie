package com.awsmovie.entity.movie.genre

import com.awsmovie.entity.BaseEntity
import com.awsmovie.entity.movie.Movie
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class MovieGenre protected constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    val movieGenreGenre: Genre,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movieGenreMovie: Movie
): BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_genre_id")
    val movieGenreId: Long? = null

    companion object {

        //============= 생성 메서드 =============//
        fun create(genre: Genre, movie: Movie): MovieGenre =
            MovieGenre(genre, movie)

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as MovieGenre

        return movieGenreId != null && movieGenreId == other.movieGenreId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(movieGenreId = $movieGenreId )"
    }

}