package com.opensource.newsapp.feature_posts.data.dto

import com.opensource.newsapp.feature_posts.domain.model.NewsDetails
import com.opensource.newsapp.feature_posts.domain.model.NewsItem

data class NewsDto(
    val response: Response
)

fun NewsDto.toNewsItem(): List<NewsItem> {
    val results = this.response.results
    return results.map {result->
        val day = result.webPublicationDate.slice(8..9)
        val month = result.webPublicationDate.slice(5..6)
        val year = result.webPublicationDate.slice(0..3)

        val productionDate = "$day-$month-$year"


        val modifiedDay = result.fields.lastModified.slice(8..9)
        val modifiedMonth = result.fields.lastModified.slice(5..6)
        val modifiedYear = result.fields.lastModified.slice(0..3)

        val modificationDate = "$modifiedDay-$modifiedMonth-$modifiedYear"

        val productionOffice = result.fields.productionOffice

        val sourcePublication = result.fields.publication


        NewsItem(
            image = result.fields.thumbnail,
            title = result.webTitle,
            headline = result.fields.headline,
            time =  "$productionDate : ${result.webPublicationDate.slice(11..15)}",
            author = result.tags.map { it.firstName },//"by ${result.tags?.map { it.firstName}}",
            ratings = result.fields.starRating,
            sourcePublication = sourcePublication,
            authorsImage = result.tags.map { it.bylineImageUrl },//result.fields.thumbnail,//" ${result.tags?.map { it.bylineImageUrl}} ",
            sectionName = result.sectionName,
            bodyText = result.fields.bodyText,
            trailText = result.fields.trailText,
            body = result.fields.body,
            productionOffice = productionOffice.ifEmpty { sourcePublication },
            lastModified = modificationDate.ifEmpty { productionDate },
            tagId = result.tags.map { it.id },
            resultId = result.id,
            twitterHandle = result.tags.map { it.twitterHandle },
            bio = result.tags.map { it.bio },
            fullNames = "${result.tags.map { it.firstName }} ${result.tags.map { it.lastName }}",
            id = result.id,
            description = result.tags.map { it.description },
            firstName = result.tags.map { it.firstName },
            lastName = result.tags.map { it.lastName }
        )
    }
}

fun NewsDto.toNewsDetails(): List<NewsDetails> {
    val results = this.response.results
    return results.map {result->
        val day = result.webPublicationDate.slice(8..9)
        val month = result.webPublicationDate.slice(5..6)
        val year = result.webPublicationDate.slice(0..3)

        val productionDate = "$day-$month-$year"


        val modifiedDay = result.fields.lastModified.slice(8..9)
        val modifiedMonth = result.fields.lastModified.slice(5..6)
        val modifiedYear = result.fields.lastModified.slice(0..3)

        val modificationDate = "$modifiedDay-$modifiedMonth-$modifiedYear"

        val productionOffice = result.fields.productionOffice

        val sourcePublication = result.fields.publication


        NewsDetails(
            image = result.fields.thumbnail,
            title = result.webTitle,
            headline = result.fields.headline,
            time =  "$productionDate : ${result.webPublicationDate.slice(11..15)}",
            author = result.tags.map { it.firstName },//"by ${result.tags?.map { it.firstName}}",
            ratings = result.fields.starRating,
            sourcePublication = sourcePublication,
            authorsImage = result.tags.map { it.bylineImageUrl },//result.fields.thumbnail,//" ${result.tags?.map { it.bylineImageUrl}} ",
            sectionName = result.sectionName,
            bodyText = result.fields.bodyText,
            trailText = result.fields.trailText,
            body = result.fields.body,
            productionOffice = productionOffice.ifEmpty { sourcePublication },
            lastModified = modificationDate.ifEmpty { productionDate },
            tagId = result.tags.map { it.id },
            resultId = result.id,
            twitterHandle = result.tags.map { it.twitterHandle },
            bio = result.tags.map { it.bio },
            fullNames = "${result.tags.map { it.firstName }} ${result.tags.map { it.lastName }}",
            id = result.id,
            description = result.tags.map { it.description },
            firstName = result.tags.map { it.firstName },
            lastName = result.tags.map { it.lastName }
        )
    }
}
