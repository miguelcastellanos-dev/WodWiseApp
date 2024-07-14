package com.migueldev.wodwiseapp.domain.logger

import android.util.Log
import javax.inject.Inject

class LoggerImplementation @Inject constructor() : Logger {
    override fun e(tag: String, message: String) {
        Log.e(tag, message)
    }

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}
