package com.radlance.championshipfinal.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
        startDestination = Splash,
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
                navigateToPasswordCreation = { email, password ->
                    navController.navigate(PasswordCreation(email, password))
                },
                navigateToMainScreen = {
                    navController.navigate(Main) {
                        popUpTo<SignIn> {
                            inclusive = true
                        }
                    }
                },
                navigateToOtpEnter = { navController.navigate(OtpEnter) }
            )
        }

        composable<PasswordCreation> {
            val args = it.toRoute<PasswordCreation>()

            PasswordCreationScreen(
                navigateToProfileCreationScreen = {
                    navController.navigate(ProfileCreation(args.email, args.password))
                }
            )
        }

        composable<ProfileCreation> {
            val args = it.toRoute<ProfileCreation>()

            ProfileCreationScreen(
                navigateToHome = {
                    navController.navigate(Main) {
                        popUpTo<SignIn> { inclusive = true }
                    }
                },
                email = args.email,
                password = args.password
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
                navigateToSignIn = {
                    navController.navigate(SignIn) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                productViewModel = productViewModel
            )
        }

        composable<Cart> {
            CartScreen(onBackPressed = navController::navigateUp, viewModel = productViewModel)
        }
    }
}