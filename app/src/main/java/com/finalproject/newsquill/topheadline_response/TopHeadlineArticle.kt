package com.finalproject.newsquill.topheadline_response

data class TopHeadlineArticle(
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val source: TopHeadlineSource,
    val title: String,
    val url: String
)