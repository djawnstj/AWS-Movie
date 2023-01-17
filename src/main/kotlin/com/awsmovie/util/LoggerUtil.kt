package com.awsmovie.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> createLogger(): Logger = LoggerFactory.getLogger(T::class.java)
