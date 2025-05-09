package com.radlance.uikit.component.tabbar

import com.radlance.uikit.R

interface BottomTab {

    fun iconResId(): Int

    fun labelResId(): Int

    object Home : BottomTab {

        override fun iconResId(): Int = R.drawable.ic_home

        override fun labelResId(): Int = R.string.home
    }

    object Catalog : BottomTab {

        override fun iconResId(): Int = R.drawable.ic_catalog

        override fun labelResId(): Int = R.string.catalog
    }

    object Projects : BottomTab {

        override fun iconResId(): Int = R.drawable.ic_projects

        override fun labelResId(): Int = R.string.projects
    }

    object Profile : BottomTab {

        override fun iconResId(): Int = R.drawable.ic_profile

        override fun labelResId(): Int = R.string.profile
    }
}