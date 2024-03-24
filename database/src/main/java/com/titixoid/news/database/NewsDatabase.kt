package com.titixoid.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.titixoid.news.database.dao.ArticleDao
import com.titixoid.news.database.models.ArticleDBO

@Database(entities = [ArticleDBO::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticleDao
}

fun NewsDatabase(applicationContext: Context): NewsDatabase {
    return Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        NewsDatabase::class.java,
        "news"
    ).build()
}