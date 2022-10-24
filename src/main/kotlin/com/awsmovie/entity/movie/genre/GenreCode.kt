package com.awsmovie.entity.movie.genre

import com.awsmovie.controller.dto.movie.GenreDto

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

        /**
         * 장르 배열로 변환
         */
        fun toList(): List<GenreDto> {

            val genreList = mutableListOf<GenreDto>()

            values().forEach {
                genreList += GenreDto(it.genreCode, it.genreKrName, it.genreEnName)
            }

            return genreList
        }

    }

}