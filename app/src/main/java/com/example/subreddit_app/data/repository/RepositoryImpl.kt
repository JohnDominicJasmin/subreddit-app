package com.example.subreddit_app.data.repository

import android.util.Base64
import com.example.subreddit_app.BuildConfig
import com.example.subreddit_app.data.ApiService
import com.example.subreddit_app.data.OauthApiService
import com.example.subreddit_app.data.mapper.RedditMapper.mapToken
import com.example.subreddit_app.domain.model.TokenResponseModel
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
        ).body()
        return result?.mapToken() ?: throw Exception("Error refreshing access token")
    }
}