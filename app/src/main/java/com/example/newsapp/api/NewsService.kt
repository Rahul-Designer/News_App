package com.example.newsapp.api

import com.example.newsapp.Model.NewsResponse
import com.example.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    //    @GET("/v2/everything?q=apple&from=2022-09-04&to=2022-09-04&sortBy=popularity&apiKey=220b38415ea845af881e6d667be4b154")

//    "v2/top-headlines?country=us&category=business&apiKey=220b38415ea845af881e6d667be4b154"


    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")
        news: String = "apple",
        @Query("sortBy")
        sort : String = "latest",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

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