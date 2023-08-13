package com.finalproject.newsquill.api

import com.finalproject.newsquill.search_response.SearchArticle
import com.finalproject.newsquill.topheadline_response.TopHeadlineArticle
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("apiKey") apiKey: String,
        @Query("lang") lang: String,
        @Query("category") category: String
    ): List<TopHeadlineArticle>

    @GET("search")
    suspend fun getSearch(
        @Query("apiKey") apiKey: String,
        @Query("q") q: String
    ) : List<SearchArticle>

}