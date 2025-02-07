package com.example.subreddit_app.presentation.search_subreddits.ui.event

sealed class SearchSubredditEvent {
    data class SearchSubreddit(val query: String) : SearchSubredditEvent()

}