package com.example.subreddit_app.presentation.subreddit_detail.ui.event

sealed class SubredditDetailEvent {
    data class GetSubredditDetails(val subreddit: String) : SubredditDetailEvent()
    data class GetSubredditInfo(val subreddit: String) : SubredditDetailEvent()
}