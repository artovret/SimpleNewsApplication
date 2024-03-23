package com.titixoid.newsapi

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.titixoid.newsapi.models.ArticleDTO
import com.titixoid.newsapi.models.Lahguages
import com.titixoid.newsapi.models.ResponseDTO
import com.titixoid.newsapi.models.SortBy
import com.titixoid.newsapi.utils.TimeApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface NewsApi {
    /**
     * Documentation - [News API](https://newsapi.org/docs/endpoints/everything)
     */
    @GET("/everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") languages: List<Lahguages>? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("PageSize") @IntRange(from = 0, to = 100) pageSize: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1
    ): Result<ResponseDTO<ArticleDTO>>
}


fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): NewsApi {
    return retrofit(baseUrl, apiKey, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json = Json,
): Retrofit {
    val converterFactory = json.asConverterFactory(MediaType.get("application/json"))

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(TimeApiKeyInterceptor(apiKey)).build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory((ResultCallAdapterFactory.create()))
        .client(modifiedOkHttpClient)
        .build()
}
