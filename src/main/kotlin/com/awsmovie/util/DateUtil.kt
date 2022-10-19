package com.awsmovie.util

import java.time.format.DateTimeFormatter
import java.util.*

/**
 * LocalDateTime 관련 변수/함수를 모아놓은 객체
 */
object DateUtil {

    /** yyyyMMddHHmmss */
    val formatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.KOREAN)
    /** yyyy-MM-dd HH:mm:ss */
    val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
    /** yyyyMMddH */
    val formatter3: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddH", Locale.KOREAN)
    /** yyyy-MM-dd */
    val formatter4: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREAN)

}