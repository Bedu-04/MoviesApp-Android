package org.bedu.movies_app_android.ui.adapters

import org.bedu.movies_app_android.R


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.bedu.movies_app_android.data.models.MovieResult


class RecyclerMovieDBAdapter(

    var movies : List<MovieResult>?,
    private val goToDetailFragment: (MovieResult) -> Unit,
    private val toggleFavorites: (MovieResult) -> Unit) : RecyclerView.Adapter<RecyclerMovieDBAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies?.get(position)
        if (movie != null) {
            holder.bind(movie)
        }

        holder.favoriteCheck.setOnCheckedChangeListener { _, _ ->
            if (movie != null) {
                toggleFavorites(movie)
            }
        }

        holder.itemView.setOnClickListener{
            if (movie != null) {
                goToDetailFragment(movie)
            }
        }


    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val titleVT = view.findViewById<TextView>(R.id.title_catalog)
        private val ratingBarPopularity = view.findViewById<RatingBar>(R.id.ratingBar_catalog)
        val posterMovie = view.findViewById<ImageView>(R.id.img)
        val favoriteCheck: CheckBox = view.findViewById(R.id.cbHeart)

        fun bind(movie: MovieResult) {
            titleVT.text = movie.original_title
            favoriteCheck.isChecked = movie.isFavorite
            ratingBarPopularity.rating = movie.vote_average.toFloat()
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + movie.poster_path).into(posterMovie);
        }
    }
}