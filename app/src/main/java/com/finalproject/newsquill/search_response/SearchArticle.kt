package com.finalproject.newsquill.search_response

data class SearchArticle(
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val source: SearchSource,
    val title: String,
    val url: String
)