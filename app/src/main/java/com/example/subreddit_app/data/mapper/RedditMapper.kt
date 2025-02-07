package com.example.subreddit_app.data.mapper

import com.example.subreddit_app.core.utils.Utils
import com.example.subreddit_app.core.utils.Utils.formatSubscribers
import com.example.subreddit_app.data.data_source.network.dto.discussion_about.DiscussionAboutDto
import com.example.subreddit_app.data.data_source.network.dto.discussions.DiscussionChildren
import com.example.subreddit_app.data.data_source.network.dto.discussions.DiscussionDto
import com.example.subreddit_app.data.data_source.network.dto.search_subreddits.SearchChildren
import com.example.subreddit_app.data.data_source.network.dto.search_subreddits.SubredditSearchDto
import com.example.subreddit_app.data.data_source.network.dto.subreddits.Children
import com.example.subreddit_app.data.data_source.network.dto.subreddits.PopularSubredditsDto
import com.example.subreddit_app.data.data_source.network.dto.token.TokenResponseDto
import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditItem
import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditsModel
import com.example.subreddit_app.domain.model.search.SearchItem
import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetails
import com.example.subreddit_app.domain.model.subreddit_details.SubredditDetailsModel
import com.example.subreddit_app.domain.model.search.SubredditSearchModel
import com.example.subreddit_app.domain.model.auth.TokenResponseModel
import com.example.subreddit_app.domain.model.subreddit_details.SubredditInfoModel

object RedditMapper {
    fun TokenResponseDto.mapToken(): TokenResponseModel {
        return TokenResponseModel(
            accessToken = this.accessToken
        )
    }

    fun PopularSubredditsDto.toPopularSubreddits(): PopularSubredditsModel {
        return PopularSubredditsModel(
            popularSubredditItems = this.data.children.map { it.toPopularSubreddit() }
        )
    }

    private fun Children.toPopularSubreddit(): PopularSubredditItem {
        val data = this.data
        return PopularSubredditItem(
            primaryColor = data.primaryColor,
            iconImage = data.iconImg,
            advertiserCategory = data.advertiserCategory,
            publicDescription = data.publicDescription,
            title = data.title,
            bannerBackgroundImage = data.bannerBackgroundImage,
            url = data.url,
            subscribers = formatSubscribers(data.subscribers),
            displayNamePrefixed = data.displayNamePrefixed,
            id = data.id,
            displayName = data.displayName
        )
    }

    fun SubredditSearchDto.toSubredditSearch(): SubredditSearchModel {
        return SubredditSearchModel(
            searches = this.data.children.map { it.toSearchItem() }
        )
    }

    private fun SearchChildren.toSearchItem(): SearchItem {
        val decodedUrl = data.communityIcon.replace("&amp;", "&")

        return SearchItem(
            displayName = this.data.displayName,
            headerImage = this.data.headerImg,
            title = this.data.title,
            displayNamePrefixed = this.data.displayNamePrefixed,
            subscribers = formatSubscribers(this.data.subscribers),
            id = this.data.id,
            publicDescription = data.publicDescription,
            communityIcon = decodedUrl,
            description = data.description,
            url = data.url,
            headerTitle = data.headerTitle
        )
    }


    fun DiscussionDto.toSubredditDetails(): SubredditDetailsModel {
        return SubredditDetailsModel(
            discussions = this.data.children.map { it.toDiscussionItem() }
        )
    }

    private fun DiscussionChildren.toDiscussionItem(): SubredditDetails {
        return SubredditDetails(
            subredditName = this.data.subreddit,
            authorFullName = this.data.author,
            title = this.data.title,
            subredditNamePrefixed = this.data.subredditNamePrefixed,
            thumbnail = this.data.thumbnail,
            id = this.data.id,
            thumbnailWidth = this.data.thumbnailWidth,
            thumbnailHeight = this.data.thumbnailHeight,
            url = this.data.url,
            timeAgo = Utils.timeAgo(this.data.created)



        )
    }

    fun DiscussionAboutDto.toSubredditInfoModel(): SubredditInfoModel {
        val communityIcon = data.iconImg.replace("&amp;", "&")
        val bannerBackgroundImage = data.bannerBackgroundImage.replace("&amp;", "&")

        return SubredditInfoModel(
            displayNamePrefixed = data.displayNamePrefixed,
            mobileBannerImage = bannerBackgroundImage,
            title = data.title,
            subscribers = formatSubscribers(data.subscribers),
            url = data.url,
            communityIcon = communityIcon,
            id = data.id,
            activeUserCount = data.activeUserCount.toString()
        )
    }

}