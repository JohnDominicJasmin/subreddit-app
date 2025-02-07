package com.example.subreddit_app.domain.model.popular_subreddit

data class PopularSubredditItem(
    val primaryColor : String,
    val id : String,

    val iconImage : String,
    val advertiserCategory: String,
    val publicDescription: String,
    val title:String,
    val bannerBackgroundImage: String,
    val url: String,
    val subscribers: String,
    val displayNamePrefixed: String,
    val displayName: String
)
