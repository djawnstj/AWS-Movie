package com.awsmovie.entity.movie.genre

import com.awsmovie.entity.BaseEntity
import com.awsmovie.entity.movie.Movie
import org.hibernate.Hibernate
import javax.persistence.*

/**
 * Genre Code
 *
 *
 *
 */
@Entity
data class Genre protected constructor(
    @Enumerated(value = EnumType.STRING)
    val genre: GenreCode,
    @OneToMany(mappedBy = "movieGenreGenre", cascade = [CascadeType.REMOVE])
    val movies: List<MovieGenre> = ArrayList(),
//    @ManyToMany @JoinColumn(name = "movie_id")
//    val movies: List<Movie> = ArrayList()
): BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    val genreId: Long? = null

    companion object {

        //============= 생성 메서드 =============//
        fun createGenre(genreCode: GenreCode) = Genre(genreCode)

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Genre

        return genreId != null && genreId == other.genreId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(genreId = $genreId , createTime = $createTime , updateTime = $updateTime , genre = $genre )"
    }

}