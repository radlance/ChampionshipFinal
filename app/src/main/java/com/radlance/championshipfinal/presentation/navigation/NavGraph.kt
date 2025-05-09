package com.radlance.championshipfinal.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.championshipfinal.presentation.auth.PasswordCreationScreen
import com.radlance.championshipfinal.presentation.auth.SignInScreen
import com.radlance.championshipfinal.presentation.splash.SplashScreen
import com.radlance.uikit.theme.CustomTheme

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = modifier.background(CustomTheme.colors.white)
    ) {
        composable<Splash> {
            SplashScreen(onDelayFinished = { navController.navigate(SignIn) })
        }

        composable<SignIn> {
            SignInScreen(
                navigateToPasswordCreationScreen = {
                    navController.navigate(PasswordCreation)
                }
            )
        }

        composable<PasswordCreation> {
            PasswordCreationScreen()
        }
    }
}