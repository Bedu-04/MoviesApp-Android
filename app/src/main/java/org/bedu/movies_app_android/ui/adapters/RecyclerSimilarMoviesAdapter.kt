package org.bedu.movies_app_android.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.domain.model.Movie

class RecyclerSimilarMoviesAdapter(
    var movies : List<Movie>?
): RecyclerView.Adapter<RecyclerSimilarMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.similar_movie, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies?.get(position)

        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val movieName = view.findViewById<TextView>(R.id.title_movie)
        private val movieImg = view.findViewById<ImageView>(R.id.movie_img)

        fun bind(movie: Movie) {
            movieName.text = movie.original_title
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + movie.poster_path).into(movieImg);
        }

    }
}