package com.awsmovie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AwsMovieApplication

fun main(args: Array<String>) {
    runApplication<AwsMovieApplication>(*args)
}
