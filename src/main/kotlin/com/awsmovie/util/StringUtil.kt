package com.awsmovie.util

object StringUtil {

    fun String.isNumeric(): Boolean {
        return try {
            toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

}