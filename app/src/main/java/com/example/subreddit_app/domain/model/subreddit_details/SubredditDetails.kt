package com.example.subreddit_app.domain.model.subreddit_details

import androidx.annotation.Keep
import androidx.compose.runtime.Stable

@Keep
@Stable
data class SubredditDetails(
    val subredditName:String,
    val authorFullName: String,
    val title: String,
    val subredditNamePrefixed: String,
    val thumbnail: String,
    val id: String,
    val thumbnailWidth: Int,
    val url: String,
    val thumbnailHeight: Int,
    val timeAgo: String,
)