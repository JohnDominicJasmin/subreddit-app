# 📌 Subreddit App
This app allows users to browse and search subreddits, view posts within a subreddit, and open posts in a WebView.

## ✨ Features
- 🔹 **Browse Subreddits** – View trending subreddits.
- 🔹 **Search Subreddits** – Find subreddits by name.
- 🔹 **View Posts** – See posts from selected subreddits.
- 🔹 **Open in WebView** – Read posts in an in-app WebView.


## 📌 User Flow
1. **Home Screen:** Displays a list of trending subreddits.
2. **Select a Subreddit:** Clicking on a subreddit shows its posts.
3. **View Post:** Tapping a post opens it in a WebView.


## 📌 App Architecture
The app follows **Clean Architecture** with:
### 1️⃣ **Presentation Layer**
- **Jetpack Compose UI:** Displays subreddit and post data.
- **ViewModel:** Handles UI logic and interacts with the domain layer.
### 2️⃣ **Domain Layer**
- **Use Cases:** Business logic for subreddit and post retrieval.
- **Entities:** Core data models representing subreddit and post details.
### 3️⃣ **Data Layer**
- **Repository:** Fetches subreddit and post data via the Reddit API.

## 📌 Technologies Used
- ✅ **Kotlin** – Programming language for Android development.
- ✅ **Jetpack Compose** – UI toolkit for building Android UIs.
- ✅ **Hilt** – Dependency injection framework for Android.
- ✅ **Retrofit** – Networking library for API requests.
- ✅ **Kotlin Coroutines & Flow** – For asynchronous data handling.

## 📌 Installation
Clone the repository and run the app in Android Studio:
```sh
git clone https://github.com/JohnDominicJasmin/subreddit-app.git
```
## 📌 Download the app here:
```sh
https://drive.google.com/file/d/1SjeOOC0n68FZpGlkdkpH-1xLKXpGC5ae/view?usp=sharing
```