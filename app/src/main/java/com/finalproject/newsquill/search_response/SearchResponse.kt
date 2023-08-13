package com.finalproject.newsquill.search_response

data class SearchResponse(
    val searchArticles: List<SearchArticle>,
    val totalArticles: Int
)