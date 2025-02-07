package com.example.subreddit_app.domain.model.search

data class SearchItem(
    val displayName: String,
    val headerImage: String?,
    val title: String,
    val displayNamePrefixed: String,
    val subscribers: String,
    val id: String,
    val publicDescription: String,
    val communityIcon: String,
    val description: String,
    val url: String,
    val headerTitle: String,

)
