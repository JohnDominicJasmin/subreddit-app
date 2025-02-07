package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditsModel
import com.example.subreddit_app.domain.repository.Repository

class GetPopularSubredditUseCase (private val repository: Repository) {
    suspend operator fun invoke(accessToken: String): PopularSubredditsModel {
        return repository.getPopularSubreddits(accessToken)
    }
}