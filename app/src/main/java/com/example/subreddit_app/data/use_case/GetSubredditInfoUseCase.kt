package com.example.subreddit_app.data.use_case

import com.example.subreddit_app.domain.model.subreddit_details.SubredditInfoModel
import com.example.subreddit_app.domain.repository.Repository

class GetSubredditInfoUseCase(private val repository: Repository){
    suspend operator fun invoke(accessToken: String, subreddit: String): SubredditInfoModel {
        return repository.getSubredditInfo(accessToken, subreddit)
    }

}