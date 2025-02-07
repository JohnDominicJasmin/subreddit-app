package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.data.data_source.local.TokenManager
import javax.inject.Singleton

class ClearTokenUseCase(private val tokenManager: TokenManager) {
    suspend operator fun invoke() {
        tokenManager.clearToken()
    }
}