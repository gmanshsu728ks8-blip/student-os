package com.studentos.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studentos.app.feature.dashboard.DashboardScreen

@Composable
fun StudentOsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = StudentOsDestination.Dashboard.route,
        modifier = modifier,
    ) {
        composable(StudentOsDestination.Dashboard.route) {
            DashboardScreen()
        }
    }
}
