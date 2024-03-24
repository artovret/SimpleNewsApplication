package com.titixoid.news.data

import com.titixoid.news.data.models.Article
import com.titixoid.news.database.NewsDatabase
import com.titixoid.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepository(
    private val database: NewsDatabase,
    private val api: NewsApi,
) {

    fun getAll(): RequestResult<Flow<List<Article>>> {
        return RequestResult.InProgress(database.articlesDao
            .getAll()
            .map { articles -> articles.map { it.toArticle() } }
        )
    }

    suspend fun search(query: String): Flow<List<Article>> {
        api.everything()
        TODO("Not yet")
    }
}

sealed class RequestResult<E>(protected val data: E?) {
    class InProgress<E>(data: E?) : RequestResult<E>(data)
    class Success<E>(data: E?) : RequestResult<E>(data)
    class Error<E>(data: E?) : RequestResult<E>(data)
}