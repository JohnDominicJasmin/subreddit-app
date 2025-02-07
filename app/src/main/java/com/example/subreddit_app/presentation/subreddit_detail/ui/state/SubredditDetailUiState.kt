package com.example.subreddit_app.presentation.subreddit_detail.ui.state

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetailsModel
import com.example.subreddit_app.domain.model.subreddit_details.SubredditInfoModel


@Keep
@Stable
data class SubredditDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val accessToken: String? = null,
    val subredditInfoModel: SubredditInfoModel? = null,
    val subredditDetailsModel: SubredditDetailsModel? = null
)
