package com.example.mobvies.remote.repositories

import android.content.Context
import com.example.mobvies.R
import java.util.*

open class BaseUseCase(
    private val context : Context
){
    protected fun getApiKey() : String = context.getString(R.string.API_KEY_TMDB)
    protected fun getLocale() : String = Locale.getDefault().displayLanguage
}