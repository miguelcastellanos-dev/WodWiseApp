package com.migueldev.wodwiseapp.data.mapper

import java.util.Locale

interface LocaleProvider {
    fun getLocale(): Locale
}

class DefaultLocaleProvider : LocaleProvider {
    override fun getLocale(): Locale = Locale.getDefault()
}
