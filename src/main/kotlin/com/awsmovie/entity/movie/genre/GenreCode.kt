package com.awsmovie.entity.movie.genre

enum class GenreCode(
    val genreCode: Int,
    val genreKrName: String,
    val genreEnName: String
) {

    ROMANCE(1, "로맨스", "romance"),
    ACTION(2, "액션", "action"),;

    companion object {

        fun of(genreCode: Int): GenreCode {

            GenreCode.values().forEach {
                if (it.genreCode == genreCode) return it
            }

            throw IllegalArgumentException("일치하는 장르 코드가 없습니다.")

        }

    }

}