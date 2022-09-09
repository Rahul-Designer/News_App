package com.example.newsapp.api

import com.example.newsapp.Model.NewsResponse
import com.example.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    // https://newsapi.org/v2/everything?q=apple&sortBy=latest&apiKey=220b38415ea845af881e6d667be4b154

    // show in recyclerview
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")
        news: String = "apple",
        @Query("sortBy")
        sort : String = "latest",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    // Search News
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        news: String = "apple",
        @Query("sortBy")
        sort : String = "latest",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>


}