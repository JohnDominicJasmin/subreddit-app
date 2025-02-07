package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.domain.model.search.SubredditSearchModel
import com.example.subreddit_app.domain.repository.Repository

class SearchSubredditUseCase (private val repository: Repository) {
    suspend operator fun invoke(accessToken: String, query: String): SubredditSearchModel {
        return repository.searchSubreddits(accessToken, query)
    }

}