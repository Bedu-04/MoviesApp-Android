package org.bedu.movies_app_android.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.models.MovieResult

class RecyclerCharacterMovieAdapter(
    private var movies : List<MovieResult>,
) : RecyclerView.Adapter<RecyclerCharacterMovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerCharacterMovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerCharacterMovieAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val titleVT = view.findViewById<TextView>(R.id.title_catalog)
        private val ratingBarVT = view.findViewById<RatingBar>(R.id.ratingBar_catalog)
        private val imageIV = view.findViewById<ImageView>(R.id.img)
        val favoritesVT = view.findViewById<CheckBox>(R.id.cbHeart)

        fun bind(movie: MovieResult) {
            /*titleVT.text = movie.name.toString()
            ratingBarVT.rating = movie.rating.toFloat()
            imageIV.setImageResource(movie.image)
            favoritesVT.isChecked = movie.isFavorite*/

            titleVT.text = movie.original_title
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + movie.poster_path).into(imageIV);
        }

    }
}