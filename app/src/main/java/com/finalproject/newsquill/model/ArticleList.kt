package com.finalproject.newsquill.model

import com.finalproject.newsquill.common.Article

data class ArticleList(
    val articles : List<Article>,
    val totalArticles: Int
)
