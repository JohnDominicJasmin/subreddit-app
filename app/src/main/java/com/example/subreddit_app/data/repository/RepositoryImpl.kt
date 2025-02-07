package com.example.subreddit_app.data.repository

import android.util.Base64
import com.example.subreddit_app.BuildConfig
import com.example.subreddit_app.data.ApiService
import com.example.subreddit_app.data.OauthApiService
import com.example.subreddit_app.data.data_source.local.NetworkConnectivity
import com.example.subreddit_app.data.mapper.RedditMapper.mapToken
import com.example.subreddit_app.data.mapper.RedditMapper.toSubredditDetails
import com.example.subreddit_app.data.mapper.RedditMapper.toPopularSubreddits
import com.example.subreddit_app.data.mapper.RedditMapper.toSubredditInfoModel
import com.example.subreddit_app.data.mapper.RedditMapper.toSubredditSearch
import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditsModel
import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetailsModel
import com.example.subreddit_app.domain.model.search.SubredditSearchModel
import com.example.subreddit_app.domain.model.auth.TokenResponseModel
import com.example.subreddit_app.domain.model.subreddit_details.SubredditInfoModel
import com.example.subreddit_app.domain.repository.Repository

class RepositoryImpl (private val api: ApiService, private val oauthApi: OauthApiService) : Repository{
    override suspend fun refreshAccessToken(
       ): TokenResponseModel {
        val username = BuildConfig.REDDIT_USERNAME
        val password = BuildConfig.REDDIT_PASSWORD
        val clientId = BuildConfig.REDDIT_CLIENT_ID
        val clientSecret = BuildConfig.REDDIT_CLIENT_SECRET

        val credentials = "$clientId:$clientSecret"
        val authHeader = "Basic ${Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)}"

        val result = api.refreshAccessToken(
            authorization = authHeader,
            username = username,
            password = password
        )
        return result.mapToken() ?: throw Exception("Error refreshing access token")
    }
    private fun getAuthorization(accessToken: String) = "Bearer $accessToken"
    override suspend fun getPopularSubreddits(accessToken: String): PopularSubredditsModel {
        val authorization = getAuthorization(accessToken)
        return oauthApi.getPopularSubreddits(authorization).toPopularSubreddits()
    }

    override suspend fun searchSubreddits(accessToken: String, query: String): SubredditSearchModel {
        val authorization = getAuthorization(accessToken)
        return oauthApi.searchSubreddits(authorization, query = query).toSubredditSearch()
    }

    override suspend fun getSubredditDetails(
        accessToken: String,
        subreddit: String): SubredditDetailsModel {
        val authorization = getAuthorization(accessToken)

        return oauthApi.getSubredditDetails(authorization = authorization, subreddit = subreddit).toSubredditDetails()
    }

    override suspend fun getSubredditInfo(
        accessToken: String,
        subreddit: String): SubredditInfoModel {

        val authorization = getAuthorization(accessToken)
        return oauthApi.getSubredditInfo(authorization = authorization, subreddit = subreddit).toSubredditInfoModel()

    }

}