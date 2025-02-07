package com.example.subreddit_app.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.subreddit_app.presentation.subreddits.ui.screens.SubRedditsScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SubReddits.route) {
        composable(Screens.SubReddits.route) {
//            UsersScreen(navController = navController)
            SubRedditsScreen(navController = navController)
        }
        composable(
            route = Screens.RedditDetails.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
//            ProfileScreen(navController = navController, id = id, numberOfResults = numberOfResult)
        }
    }
}


fun NavController.navigateScreen(
    route: String) {
    navigate(route) {
        popUpTo(route) {
            saveState = true

        }
        restoreState = true
        launchSingleTop = true
    }
}

fun NavController.goBack() {
    if (previousBackStackEntry != null) {
        popBackStack()
    }
}