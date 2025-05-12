package com.radlance.championshipfinal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class BottomNavigationState(val navHostController: NavHostController) {

    fun <T : Any> navigateTo(route: T, popUpToStart: Boolean = false) {
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true

            if (popUpToStart) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }
}

@Composable
fun rememberNavigationState(
    navController: NavHostController = rememberNavController()
): BottomNavigationState = remember { BottomNavigationState(navController) }
