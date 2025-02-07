package com.example.subreddit_app.ui.navigation


sealed class Screens(val route: String) {

    data object SubReddits : Screens("subreddits")

    data object RedditDetails : Screens("reddit_details?id={id}") {
        fun passArguments(id: String): String {
            return "user_details?id=$id&numberOfResult"
        }
    }
}
