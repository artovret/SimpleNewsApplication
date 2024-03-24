package com.titixoid.news.data

import com.titixoid.news.data.models.Article
import com.titixoid.news.database.NewsDatabase
import com.titixoid.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow

class ArticlesRepository(
    private val database: NewsDatabase,
    private val api: NewsApi,
) {

    fun request(): Flow<Article> {
        TODO("Not yet")
    }
}