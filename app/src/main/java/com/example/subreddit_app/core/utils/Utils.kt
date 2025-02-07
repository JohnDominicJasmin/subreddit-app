package com.example.subreddit_app.core.utils

object Utils {
    fun formatSubscribers(count: Int): String {
        return when {
            count >= 1_000_000 -> "${count / 1_000_000}M"
            count >= 100_000 -> "${count / 1_000}K"
            else -> count.toString()
        }
    }
    fun timeAgo(timestamp: Double): String {
        val currentTime = System.currentTimeMillis() / 1000  // Convert to seconds
        val timeDiff = currentTime - timestamp.toLong() // Difference in seconds

        return when {
            timeDiff < 60 -> "just now"
            timeDiff < 3600 -> "${timeDiff / 60} minutes ago"
            timeDiff < 86400 -> "${timeDiff / 3600} hours ago"
            timeDiff < 604800 -> "${timeDiff / 86400} days ago"
            timeDiff < 2592000 -> "${timeDiff / 604800} weeks ago"
            timeDiff < 31536000 -> "${timeDiff / 2592000} months ago"
            else -> "${timeDiff / 31536000} years ago"
        }
    }
}