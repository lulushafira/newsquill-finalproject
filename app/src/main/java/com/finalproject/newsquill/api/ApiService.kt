package com.finalproject.newsquill.api

import com.finalproject.newsquill.model.ArticleList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("apikey") apiKey: String,
        @Query("lang") lang: String,
        @Query("category") category: String
    ): ArticleList

    @GET("search")
    suspend fun getSearch(
        @Query("apikey") apiKey: String,
        @Query("q") q: String
    ) : ArticleList

}