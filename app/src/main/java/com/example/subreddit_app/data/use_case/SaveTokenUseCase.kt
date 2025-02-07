package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.data.data_source.local.TokenManager


class SaveTokenUseCase(private val tokenManager: TokenManager) {
    suspend operator fun invoke(token: String) {
        tokenManager.saveToken(token)
    }
}