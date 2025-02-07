package com.example.subreddit_app.ui.navigation

import android.net.Uri


sealed class Screens(val route: String) {

    data object SubReddits : Screens("subreddits")

    data object RedditDetails : Screens("reddit_details?subredditName={subredditName}") {
        fun passArguments(subredditName: String): String {
            return "reddit_details?subredditName=${Uri.encode(subredditName)}"
        }
    }
    data object SearchSubreddits : Screens("search_subreddits") {

    }
    data object WebViewScreen : Screens("webview?url={url}") {
        fun passArguments(url: String): String {
            return "webview?url=${Uri.encode(url)}"
        }
    }
}
