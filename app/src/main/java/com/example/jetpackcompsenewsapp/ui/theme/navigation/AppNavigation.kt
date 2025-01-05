package com.example.jetpackcompsenewsapp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompsenewsapp.ui.theme.screens.HomeScreen

// created Navigation graph for navigate
// all the navigation related things we going to keep here in navigation package

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routs.HOME_SCREEN
    ) {
        composable(Routs.HOME_SCREEN) {
            // Pass the navBackStackEntry to the HomeScreen composable
            HomeScreen()
        }
    }
}


/*@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()


    NavHost(
        navController = navController, startDestination = Routs.HOME_SCREEN) {
        composable(Routs.HOME_SCREEN) {
            HomeScreen()
        }
    }
}*/


