package com.example.subreddit_app.presentation.subreddits.ui.state

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditsModel
import com.example.subreddit_app.domain.model.auth.TokenResponseModel

@Keep
@Stable
data class SubRedditUiState(
    val isLoading: Boolean = false,
    val token: TokenResponseModel? = null,
    val error: String? = null,
    val popularSubredditsModel: PopularSubredditsModel? = null

)
