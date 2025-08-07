package com.gabriel.cineverse.data.remote

import com.gabriel.cineverse.data.model.MovieDetails
import com.gabriel.cineverse.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "pt-BR"
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "pt-BR"
    ): MovieDetails
}