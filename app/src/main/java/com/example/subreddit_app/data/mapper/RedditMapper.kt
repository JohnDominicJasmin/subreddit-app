package com.example.subreddit_app.data.mapper

import com.example.subreddit_app.data.data_source.network.dto.token.TokenResponseDto
import com.example.subreddit_app.domain.model.TokenResponseModel

object RedditMapper {
    fun TokenResponseDto.mapToken(): TokenResponseModel {
        return TokenResponseModel(
            accessToken = this.accessToken
        )
    }
}