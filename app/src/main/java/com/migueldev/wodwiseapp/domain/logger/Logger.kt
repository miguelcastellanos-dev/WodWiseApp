package com.migueldev.wodwiseapp.domain.logger

interface Logger {
    fun e(tag: String, message: String)
    fun d(tag: String, message: String)
}
