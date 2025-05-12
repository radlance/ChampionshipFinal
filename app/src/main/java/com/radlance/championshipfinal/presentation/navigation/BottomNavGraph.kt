package com.radlance.championshipfinal.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.radlance.championshipfinal.presentation.catalog.CatalogScreen
import com.radlance.championshipfinal.presentation.home.HomeScreen
import com.radlance.championshipfinal.presentation.home.ProductViewModel
import com.radlance.championshipfinal.presentation.profile.ProfileScreen
import com.radlance.championshipfinal.presentation.project.ProjectCreationScreen
import com.radlance.championshipfinal.presentation.project.ProjectDetailsScreen
import com.radlance.championshipfinal.presentation.project.ProjectScreen
import com.radlance.championshipfinal.presentation.project.ProjectViewModel
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
    val projectViewModel = hiltViewModel<ProjectViewModel>()

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

        navigation<Projects>(startDestination = Projects.List) {
            composable<Projects.List> {
                ProjectScreen(
                    navigateToProjectDetails = {
                        navigationState.navigateTo(Projects.Details(it), popUpToStart = false)
                    },
                    navigateToProjectCreation = {
                        navigationState.navigateTo(Projects.Create, popUpToStart = false)
                    },
                    viewModel = projectViewModel
                )
            }

            composable<Projects.Create> {
                ProjectCreationScreen(
                    navigateUp = navController::navigateUp,
                    viewModel = projectViewModel
                )
            }

            composable<Projects.Details> {
                val args = it.toRoute<Projects.Details>()

                ProjectDetailsScreen(
                    projectId = args.projectId,
                    navigateUp = navController::navigateUp,
                    viewModel = projectViewModel
                )
            }
        }


        composable<Profile> {
            ProfileScreen()
        }
    }
}