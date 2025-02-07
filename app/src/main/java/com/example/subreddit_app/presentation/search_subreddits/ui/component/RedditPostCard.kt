package com.example.subreddit_app.presentation.search_subreddits.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun RedditPostCard(
    postImageUrl: String,
    username: String,
    timeAgo: String,
    postText: String,
    onClickPost: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp).clickable { onClickPost() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "u/$username",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "• $timeAgo",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Text(
                text = postText,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        AsyncImage(
            model = postImageUrl,
            contentDescription = "Post Image",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1A1A1A)
@Composable
fun RedditPostCardPreview() {
    MaterialTheme {
        RedditPostCard(
            postImageUrl = "https://example.com/post.jpg", // Replace with actual URL
            username = "u/RadicalSnowdude",
            timeAgo = "7 hr. ago",
            postText = "I bought my first bike!",
            onClickPost = {}
        )
    }
}
