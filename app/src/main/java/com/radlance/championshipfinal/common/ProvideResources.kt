package com.radlance.championshipfinal.common

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface ProvideResources {

    fun string(@StringRes id: Int): String

    class Base @Inject constructor(private val context: Context) : ProvideResources {

        override fun string(id: Int): String = context.getString(id)
    }
}