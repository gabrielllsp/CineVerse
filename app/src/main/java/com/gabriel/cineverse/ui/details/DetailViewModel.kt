package com.gabriel.cineverse.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.cineverse.data.model.MovieDetails
import com.gabriel.cineverse.data.remote.RetrofitClient
import com.gabriel.cineverse.data.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository = MovieRepository(RetrofitClient.apiService)

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val details = repository.getMovieDetails(movieId)
            details?.let {
                _movieDetails.value = it
            }
            _isLoading.value = false
        }
    }
}