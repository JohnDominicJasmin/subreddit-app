package com.example.subreddit_app.domain.model.subreddit_details

import androidx.compose.runtime.Stable

@Stable
data class SubredditDetailsModel(
    val discussions: List<SubredditDetails> = emptyList()
)
