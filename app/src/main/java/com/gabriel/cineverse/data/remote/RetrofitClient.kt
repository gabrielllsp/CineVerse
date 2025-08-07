package com.gabriel.cineverse.data.remote

import com.gabriel.cineverse.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private const val API_KEY = BuildConfig.TMDB_API_KEY

    val apiService: ApiService by lazy {

        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url

                val urlWithApiKey = originalUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                val newRequest = originalRequest.newBuilder()
                    .url(urlWithApiKey)
                    .build()

                chain.proceed(newRequest)
            }
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}