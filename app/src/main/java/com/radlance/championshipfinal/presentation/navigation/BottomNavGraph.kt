package com.radlance.championshipfinal.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.championshipfinal.presentation.catalog.CatalogScreen
import com.radlance.championshipfinal.presentation.home.HomeScreen
import com.radlance.championshipfinal.presentation.home.ProductViewModel
import com.radlance.uikit.component.tabbar.Catalog
import com.radlance.uikit.component.tabbar.Home
import com.radlance.uikit.component.tabbar.Profile
import com.radlance.uikit.component.tabbar.Projects
import com.radlance.uikit.theme.CustomTheme

@Composable
fun BottomNavGraph(
    navigateToCart: () -> Unit,
    navigationState: BottomNavigationState,
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val navController = navigationState.navHostController

    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier.background(CustomTheme.colors.white)
    ) {
        composable<Home> {
            HomeScreen(viewModel = productViewModel)
        }

        composable<Catalog> {
            CatalogScreen(navigateToCart = navigateToCart, viewModel = productViewModel)
        }

        composable<Projects> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = "Projects")
            }
        }

        composable<Profile> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = "Profile")
            }
        }
    }
}