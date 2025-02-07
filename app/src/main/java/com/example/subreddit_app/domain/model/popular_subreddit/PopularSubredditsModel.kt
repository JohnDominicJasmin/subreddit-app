package com.example.subreddit_app.domain.model.popular_subreddit

import androidx.compose.runtime.Stable

@Stable
data class PopularSubredditsModel(
    val popularSubredditItems: List<PopularSubredditItem> = emptyList(),
)
