package com.radlance.uikit.component.tabbar

import androidx.annotation.Keep
import com.radlance.uikit.R
import kotlinx.serialization.Serializable

interface BottomTab {

    fun iconResId(): Int

    fun labelResId(): Int
}

@Keep
@Serializable
object Home : BottomTab {

    override fun iconResId(): Int = R.drawable.ic_home

    override fun labelResId(): Int = R.string.home
}

@Keep
@Serializable
object Catalog : BottomTab {

    override fun iconResId(): Int = R.drawable.ic_catalog

    override fun labelResId(): Int = R.string.catalog
}

@Keep
@Serializable
object Projects : BottomTab {

    override fun iconResId(): Int = R.drawable.ic_projects

    override fun labelResId(): Int = R.string.projects

    @Keep
    @Serializable
    object List

    @Keep
    @Serializable
    object Create

    @Keep
    @Serializable
    data class Details(val projectId: Int)
}

@Keep
@Serializable
object Profile : BottomTab {

    override fun iconResId(): Int = R.drawable.ic_profile

    override fun labelResId(): Int = R.string.profile
}