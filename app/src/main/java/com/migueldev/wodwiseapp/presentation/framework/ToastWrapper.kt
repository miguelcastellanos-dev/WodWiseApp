package com.migueldev.wodwiseapp.presentation.framework

import android.content.Context
import android.widget.Toast

class ToastWrapper(private val context: Context) {

    fun show(resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
    }

    fun show(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
