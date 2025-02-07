package com.example.subreddit_app.core.di


import android.content.Context
import com.example.subreddit_app.core.utils.Constants
import com.example.subreddit_app.core.utils.Constants.HEADER_CACHE_CONTROL
import com.example.subreddit_app.core.utils.Constants.HEADER_PRAGMA
import com.example.subreddit_app.core.utils.EditedTypeAdapter
import com.example.subreddit_app.data.ApiService
import com.example.subreddit_app.data.OauthApiService
import com.example.subreddit_app.data.data_source.local.NetworkConnectivity
import com.example.subreddit_app.data.data_source.local.TokenManager
import com.example.subreddit_app.data.repository.RepositoryImpl
import com.example.subreddit_app.domain.repository.Repository
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }.value
    }

    @Provides
    @Singleton
    fun provideOauthApiService(okHttpClient: OkHttpClient): OauthApiService {
        val gson = GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, EditedTypeAdapter())
            .create()

        return lazy {
            Retrofit.Builder()
                .baseUrl(Constants.REDDIT_OAUTH_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OauthApiService::class.java)
        }.value
    }




    @Provides
    @Singleton
    fun provideRedditRepository(api: ApiService, oauthApiService: OauthApiService): Repository {
        return RepositoryImpl(
            api = api,
            oauthApi = oauthApiService
        )
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            if (!NetworkConnectivity(context).hasInternet()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
        val httpCacheDirectory = File(context.cacheDir, "offlineCache")
        val cacheSize = 50 * 1024 * 1024
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        return lazy {
            OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
        }.value
    }



        @Provides
        @Singleton
        fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
            return TokenManager(context)
        }

}