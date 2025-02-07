package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetailsModel
import com.example.subreddit_app.domain.repository.Repository

class GetSubredditDetailsUseCase (private val repository: Repository) {
    suspend operator fun invoke(accessToken: String, subreddit: String): SubredditDetailsModel {
        return repository.getSubredditDetails(accessToken, subreddit)
    }
}