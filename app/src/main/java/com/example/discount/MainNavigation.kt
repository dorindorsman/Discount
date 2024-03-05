package com.example.discount

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.discount.MainPage.CNN
import com.example.discount.MainPage.Welcome
import com.example.discount.cnn.CnnView
import com.example.discount.cnn.CnnViewModel
import com.example.discount.welcome.WelcomeView
import com.example.discount.welcome.WelcomeViewModel

object MainPage {
    const val Welcome = "Welcome"
    const val CNN = "CNN"
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    val appContext = LocalContext.current.applicationContext
    NavHost(navController, startDestination = Welcome, modifier = modifier) {
        composable(route = Welcome) {
            WelcomeView(
                WelcomeViewModel()
            )
        }

        composable(route = CNN) {
            CnnView(
                CnnViewModel(),
                navController.popBackStack()
            )
        }
    }

}