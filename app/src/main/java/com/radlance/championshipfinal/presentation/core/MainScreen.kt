package com.radlance.championshipfinal.presentation.core

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.radlance.championshipfinal.presentation.navigation.BottomNavGraph
import com.radlance.championshipfinal.presentation.navigation.rememberNavigationState
import com.radlance.uikit.component.tabbar.BottomTabBar
import com.radlance.uikit.component.tabbar.Catalog
import com.radlance.uikit.component.tabbar.Home
import com.radlance.uikit.component.tabbar.Profile
import com.radlance.uikit.component.tabbar.Projects

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    bottomContentPadding: Dp,
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {

    val navigationState = rememberNavigationState()

    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
    val tabs = listOf(Home, Catalog, Projects, Profile)

    Scaffold(
        bottomBar = {
            BottomTabBar(
                tabs = tabs,
                routes = navBackStackEntry?.destination?.hierarchy?.map { it.route },
                onTabClick = { navigationState.navigateTo(it) },
                bottomPadding = bottomContentPadding
            )
        },
        modifier = modifier
    ) {
        BottomNavGraph(navigationState = navigationState, navigateToCart = navigateToCart)
    }
}