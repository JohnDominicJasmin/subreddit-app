package com.example.subreddit_app.domain.repository

import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditsModel
import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetailsModel
import com.example.subreddit_app.domain.model.search.SubredditSearchModel
import com.example.subreddit_app.domain.model.auth.TokenResponseModel
import com.example.subreddit_app.domain.model.subreddit_details.SubredditInfoModel

interface Repository {
    suspend fun refreshAccessToken(): TokenResponseModel
    suspend fun getPopularSubreddits(accessToken: String): PopularSubredditsModel
    suspend fun searchSubreddits(accessToken: String, query: String): SubredditSearchModel
    suspend fun getSubredditDetails(accessToken: String, subreddit: String): SubredditDetailsModel
    suspend fun getSubredditInfo(accessToken: String, subreddit: String): SubredditInfoModel
}