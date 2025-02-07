package com.example.subreddit_app.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.subreddit_app.presentation.search_subreddits.ui.screens.SearchSubredditScreen
import com.example.subreddit_app.presentation.subreddit_detail.ui.screen.SubredditDiscussion
import com.example.subreddit_app.presentation.subreddits.ui.screens.SubRedditsScreen
import com.example.subreddit_app.presentation.webview.WebViewScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SubReddits.route) {
        composable(Screens.SubReddits.route) {
            SubRedditsScreen(navController = navController)
        }
            composable(
                route = Screens.RedditDetails.route,
                arguments = listOf(
                    navArgument("subredditName") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val subredditName = backStackEntry.arguments?.getString("subredditName") ?: ""
                SubredditDiscussion(navController = navController, subredditName = subredditName)
            }

        composable(route = Screens.SearchSubreddits.route) {
            SearchSubredditScreen(navController = navController)
        }
        composable(
            route = Screens.WebViewScreen.route,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: "https://www.reddit.com"
            WebViewScreen(navController = navController, url = url)
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