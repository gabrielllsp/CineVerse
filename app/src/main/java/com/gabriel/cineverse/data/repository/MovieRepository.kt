package com.gabriel.cineverse.data.repository

import com.gabriel.cineverse.data.model.Movie
import com.gabriel.cineverse.data.model.MovieDetails
import com.gabriel.cineverse.data.remote.ApiService

class MovieRepository(private val apiService: ApiService) {

    suspend fun getPopularMovies(): List<Movie> {

        return try {
            val response = apiService.getPopularMovies()
            response.movies
        } catch (e: Exception) {

            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails? {
        return try {
            apiService.getMovieDetails(movieId = movieId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}