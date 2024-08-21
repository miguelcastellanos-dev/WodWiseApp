package com.migueldev.wodwiseapp.presentation.framework

import android.content.Context
import javax.inject.Inject

class ResourceProvider @Inject constructor(private val context: Context) {

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}
