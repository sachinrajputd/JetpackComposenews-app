package com.example.jetpackcompsenewsapp.data.entity

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val author: String?,        // Nullable
    val title: String?,         // Nullable
    val description: String?,   // Nullable
    val url: String,            // Assume URL is always present
    val urlToImage: String?,    // Nullable
    val publishedAt: String?,   // Nullable
    val content: String?,       // Nullable
    val source: Source          // Source is non-nullable because it is required
)

data class Source(
    val id: String?,            // Nullable, some sources may not have an ID
    val name: String            // Assume name is always present
)



