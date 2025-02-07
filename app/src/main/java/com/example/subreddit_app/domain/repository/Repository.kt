package com.example.subreddit_app.domain.repository

import com.example.subreddit_app.domain.model.TokenResponseModel

interface Repository {
    suspend fun refreshAccessToken(): TokenResponseModel
    suspend fun getPopularSubreddits(): List<String>

    
}