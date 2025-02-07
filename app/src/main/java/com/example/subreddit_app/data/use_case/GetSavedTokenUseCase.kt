package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.data.data_source.local.TokenManager
import kotlinx.coroutines.flow.Flow

class GetSavedTokenUseCase(private val tokenManager: TokenManager) {
    operator fun invoke(): Flow<String?> {
        return tokenManager.accessToken
    }
}