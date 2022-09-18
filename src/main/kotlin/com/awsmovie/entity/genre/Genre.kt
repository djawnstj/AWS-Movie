package com.awsmovie.entity.genre

import com.awsmovie.entity.BaseEntity
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
    val genre: Int
): BaseEntity() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    val genreId: Long? = null

    companion object {

        //============= 생성 메서드 =============//
        fun createGenre(genreCode: Int) = Genre(genreCode)

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