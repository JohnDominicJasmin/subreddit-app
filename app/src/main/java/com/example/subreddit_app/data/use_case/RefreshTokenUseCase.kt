package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.domain.model.auth.TokenResponseModel
import com.example.subreddit_app.domain.repository.Repository

class RefreshTokenUseCase (private val repository: Repository) {
    suspend operator fun invoke(): TokenResponseModel {
        return repository.refreshAccessToken()
    }
}