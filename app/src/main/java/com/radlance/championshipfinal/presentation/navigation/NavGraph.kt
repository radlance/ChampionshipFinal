package com.radlance.championshipfinal.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.championshipfinal.presentation.auth.core.PasswordCreationScreen
import com.radlance.championshipfinal.presentation.auth.core.ProfileCreationScreen
import com.radlance.championshipfinal.presentation.auth.core.SignInScreen
import com.radlance.championshipfinal.presentation.auth.recover.OtpEnterScreen
import com.radlance.championshipfinal.presentation.auth.recover.ResetPasswordScreen
import com.radlance.championshipfinal.presentation.catalog.CartScreen
import com.radlance.championshipfinal.presentation.core.MainScreen
import com.radlance.championshipfinal.presentation.home.ProductViewModel
import com.radlance.championshipfinal.presentation.splash.SplashScreen
import com.radlance.uikit.theme.CustomTheme

@Composable
fun NavGraph(
    contentPadding: PaddingValues,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Main,
        modifier = modifier.background(CustomTheme.colors.white)
    ) {
        composable<Splash> {
            SplashScreen(
                onDelayFinished = {
                    navController.navigate(SignIn) {
                        popUpTo<Splash> { inclusive = true }
                    }
                }
            )
        }

        composable<SignIn> {
            SignInScreen(
                navigateToPasswordCreation = {
                    navController.navigate(PasswordCreation)
                },
                navigateToOtpEnter = { navController.navigate(OtpEnter) }
            )
        }

        composable<PasswordCreation> {
            PasswordCreationScreen(
                navigateToProfileCreationScreen = {
                    navController.navigate(ProfileCreation)
                }
            )
        }

        composable<ProfileCreation> {
            ProfileCreationScreen(
                navigateToHome = {
                    navController.navigate(Main) {
                        popUpTo<SignIn> { inclusive = true }
                    }
                }
            )
        }

        composable<OtpEnter> {
            OtpEnterScreen(
                onBackPressed = navController::navigateUp,
                navigateToResetPassword = { navController.navigate(ResetPassword) }
            )
        }

        composable<ResetPassword> {
            ResetPasswordScreen(
                onBackPressed = {
                    navController.navigate(SignIn) { popUpTo<SignIn>() }
                },
                navigateToSignInScreen = {
                    navController.popBackStack(route = SignIn, inclusive = false)
                }
            )
        }

        composable<Main> {
            MainScreen(
                bottomContentPadding = contentPadding.calculateBottomPadding(),
                navigateToCart = { navController.navigate(Cart) },
                productViewModel = productViewModel
            )
        }

        composable<Cart> {
            CartScreen(onBackPressed = navController::navigateUp, viewModel = productViewModel)
        }
    }
}