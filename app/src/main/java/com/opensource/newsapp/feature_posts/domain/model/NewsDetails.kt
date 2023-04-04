package com.opensource.newsapp.feature_posts.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class NewsDetails(
    val tagId: List<String>?,
    val resultId: String?,
    val twitterHandle: List<String>?,
    val image: String? = null,
    val title: String? = null,
    val headline: String? = null,
    val time: String? = null,
    val author: List<String>? = emptyList(),
    val ratings: String? = null,
    val sourcePublication: String? = null,
    val authorsImage: List<String>? = emptyList(),//List<String>? = emptyList(), //checkOn
    val sectionName: String? = null,
    val bodyText: String? = null,
    val trailText: String? = null,
    val body: String? = null,
    val bio: List<String>?,
    val productionOffice: String? = null,
    val lastModified: String? = null,
    val fullNames: String?,
    val id: String?,
    val description: List<String>?=null,
    val lastName: List<String>?=null,
    val firstName: List<String>? =null
): Parcelable