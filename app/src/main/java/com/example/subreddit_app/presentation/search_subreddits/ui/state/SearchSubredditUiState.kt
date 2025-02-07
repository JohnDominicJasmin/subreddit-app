package com.example.subreddit_app.presentation.search_subreddits.ui.state

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import com.example.subreddit_app.domain.model.search.SubredditSearchModel

@Keep
@Stable
data class SearchSubredditUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val accessToken: String? = null,
    val subredditSearchModel: SubredditSearchModel? = null

)
