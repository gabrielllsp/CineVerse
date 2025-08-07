package com.gabriel.cineverse.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gabriel.cineverse.data.model.Movie
import com.gabriel.cineverse.databinding.ItemMovieBinding

class MovieAdapter(
    private val movies: List<Movie>,
    private val onMovieClick: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val baseUrl = "https://image.tmdb.org/t/p/w500"

        fun bind(movie: Movie, onMovieClick: (Movie) -> Unit) {
            binding.textViewTitle.text = movie.title

            binding.imageViewPoster.load(baseUrl + movie.posterPath) {
                crossfade(true)
                // placeholder(R.drawable.ic_placeholder)
                // error(R.drawable.ic_error)
            }

            itemView.setOnClickListener {
                onMovieClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onMovieClick)
    }
}