package com.opensource.newsapp.feature_posts.data.dto

data class Tag(
    val apiUrl: String,
    val bio: String,
    val bylineImageUrl: String,
    val bylineLargeImageUrl: String,
    val campaignInformationType: String,
    val description: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val references: List<Any>,
    val sectionId: String,
    val sectionName: String,
    val twitterHandle: String,
    val type: String,
    val webTitle: String,
    val webUrl: String
)