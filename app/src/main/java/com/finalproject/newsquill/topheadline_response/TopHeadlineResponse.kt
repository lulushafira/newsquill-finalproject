package com.finalproject.newsquill.topheadline_response

data class TopHeadlineResponse(
    val topHeadlineArticles: List<TopHeadlineArticle>,
    val totalArticles: Int
)