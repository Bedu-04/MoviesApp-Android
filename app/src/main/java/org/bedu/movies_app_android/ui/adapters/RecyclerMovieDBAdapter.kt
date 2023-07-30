package org.bedu.movies_app_android.ui.adapters

import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.ui.presenters.ENTITIES


class RecyclerMovieDBAdapter(
    var movies : List<Movie>?,
    private val goToDetailFragment: (Movie) -> Unit,
    private val toggleMoviesDB: (Movie, Boolean, Entity: ENTITIES) -> Unit,
) : RecyclerView.Adapter<RecyclerMovieDBAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies?.get(position)
        if (movie != null) {
            holder.bind(movie)

            holder.favoriteCheck.setOnClickListener {
                Log.d("TAG", "ME EJECUTO en el click")
                val isInsertOperation = holder.favoriteCheck.isChecked
                toggleMoviesDB(movie, isInsertOperation, ENTITIES.FAVORITES)
            }

            holder.nextToSeeCheck.setOnClickListener{
                val isInsertOperation = holder.nextToSeeCheck.isChecked
                toggleMoviesDB(movie, isInsertOperation, ENTITIES.NEXT_TO_SEE)
            }

            holder.itemView.setOnClickListener{  goToDetailFragment(movie) }


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
        val nextToSeeCheck: CheckBox = view.findViewById(R.id.cbAddBtn)

        fun bind(movie: Movie) {
            Log.d("BIND RECYCLER", movie.isFavorite.toString() + movie.original_title)
            titleVT.text = movie.original_title
            favoriteCheck.isChecked = movie.isFavorite
            nextToSeeCheck.isChecked = movie.isNextToSee
            ratingBarPopularity.rating = movie.vote_average.toFloat()
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + movie.poster_path).into(posterMovie);
        }
    }
}