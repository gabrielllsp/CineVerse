package com.gabriel.cineverse.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.cineverse.data.model.Movie
import com.gabriel.cineverse.data.remote.RetrofitClient
import com.gabriel.cineverse.data.repository.MovieRepository
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {


    private val repository = MovieRepository(RetrofitClient.apiService)
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {

        viewModelScope.launch {
            _isLoading.value = true

            val popularMovies = repository.getPopularMovies()

            _movies.value = popularMovies

            _isLoading.value = false
        }
    }
}