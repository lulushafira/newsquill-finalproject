package com.finalproject.newsquill.common

data class Article(
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String
){
    data class Source(
        val name: String,
        val url: String
    )
}
