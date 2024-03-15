package com.example.timetonicapp.navigation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.timetonicapp.R
import com.example.timetonicapp.domain.UserBooksUseCase
import com.example.timetonicapp.landing.LandingScreen
import com.example.timetonicapp.landing.LandingViewModel
import com.example.timetonicapp.login.LoginScreen
import com.example.timetonicapp.login.LoginViewModel

@Composable
fun NavigationScreen(viewModel: LoginViewModel) {
    val navigationController = rememberNavController()
    val loadingProgressBar = viewModel.progressBar.value
    NavHost(
        navController = navigationController,
        startDestination = Destination.Login.route
    ) {
        composable(Destination.Login.route) {
            if (viewModel.successLogin.value) {
                viewModel.oauthResult.value?.oU?.let { oU ->
                    viewModel.sharedPreferences.getKey()?.let { sessionKey ->
                        LaunchedEffect(key1 = Unit) {
                            navigationController.navigate(
                                route = "screenLanding/$oU/$sessionKey"
                            ) {
                                popUpTo(route = Destination.Login.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            } else {
                if (!viewModel.showErrorDialog.value) {
                    LoginScreen(
                        modifier = Modifier,
                        viewModel = LoginViewModel(LocalContext.current),
                        loadingProgressBar = loadingProgressBar,
                        onClickLogin = viewModel::onLoginClicked
                    )
                } else {
                    AlertDialog(
                        title = {
                            Text(text = stringResource(id = R.string.error_on_login))
                        },
                        text = {
                            Text(text = stringResource(id = R.string.validate_email_password))
                        },
                        onDismissRequest = { },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    viewModel.showErrorDialog.value = false
                                    viewModel.progressBar.value = false
                                }
                            ) {
                                Text(stringResource(id = R.string.validate_email_password_button_confirmation))
                            }
                        },
                    )
                }
            }
        }

        composable(
            "screenLanding/{oU}/{sessionKey}",
            arguments = listOf(
                navArgument("oU") { type = NavType.StringType },
                navArgument("sessionKey") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val oU = backStackEntry.arguments?.getString("oU") ?: ""
            val sessionKey = backStackEntry.arguments?.getString("sessionKey") ?: ""
            LandingScreen(
                viewModel = LandingViewModel(
                    oU = oU,
                    sessionKey = sessionKey,
                    userBooksUseCase = UserBooksUseCase(),
                )
            )
        }
    }
}