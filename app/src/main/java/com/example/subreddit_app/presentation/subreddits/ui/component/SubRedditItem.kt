package com.example.subreddit_app.presentation.subreddits.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.example.subreddit_app.R
import com.example.subreddit_app.domain.model.popular_subreddit.PopularSubredditItem
import com.example.subreddit_app.ui.theme.CodingChallengeTheme

@Composable
fun PopularSubredditItem(
    modifier: Modifier = Modifier,
    number: String,
    item: PopularSubredditItem,
    onItemClick: (PopularSubredditItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .wrapContentHeight()
            .clickable { onItemClick(item) }
    ) {
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = number,
                modifier = Modifier.padding(horizontal = 24.dp),
                fontWeight = FontWeight.Bold
            )


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.iconImage)
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.ic_placeholder),
                placeholder = painterResource(R.drawable.ic_placeholder),
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )

            Column(verticalArrangement = Arrangement.spacedBy(0.dp), modifier = Modifier.padding(12.dp)) {
                Text(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp),
                    text = buildAnnotatedString { append(item.displayNamePrefixed) }
                )
                Text(
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = if(item.publicDescription.isEmpty()) "No description" else item.publicDescription,
                    color = Color.Gray,
                    fontSize = TextUnit(12f, type = TextUnitType.Sp),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "${item.subscribers} members",
                    color = Color.Gray,
                    fontSize = TextUnit(12f, type = TextUnitType.Sp),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPopularSubredditItem() {
    CodingChallengeTheme{
        val mock = PopularSubredditItem(
            primaryColor = "#FF4500",
            iconImage = "https://www.redditstatic.com/gold/awards/icon/silver_512.png",
            advertiserCategory = "r/aww",
            publicDescription = "A subreddit for cute and cuddly pictures",
            title = "Aww",
            bannerBackgroundImage = "https://styles.redditmedia.com/t5_2qh1o/styles/bannerBackgroundImage_4g5v5z9v7c751.jpg",
            url = "/r/aww/",
            subscribers = "24.5M",
            displayNamePrefixed = "r/aww",
            id = "99", displayName = "aww"
        )
        PopularSubredditItem(item = mock, number = "1", onItemClick = {

        })
    }
}