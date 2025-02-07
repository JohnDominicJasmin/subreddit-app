package com.example.subreddit_app.domain.model.subreddit_details

import androidx.compose.runtime.Stable

@Stable
data class SubredditInfoModel(
    val displayNamePrefixed: String,
    val mobileBannerImage: String,
    val title: String,
    val subscribers: String,
    val url: String,
    val communityIcon: String,
    val id: String,
    val activeUserCount: String
)
