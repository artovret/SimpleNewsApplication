package com.titixoid.news.main

import com.titixoid.news.data.ArticlesRepository
import com.titixoid.news.data.models.Article
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(private val repository: ArticlesRepository) {

    operator fun invoke(): Flow<Article> {
        return repository.getAll()
    }
}