package com.finalproject.newsquill.model

data class ArticleListResponse(
    val articles : List<Article>,
    val totalArticles: Int
)
