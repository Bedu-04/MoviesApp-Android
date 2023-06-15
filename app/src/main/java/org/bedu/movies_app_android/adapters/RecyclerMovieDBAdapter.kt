package org.bedu.movies_app_android.adapters

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
import org.bedu.movies_app_android.models.MovieResult


class RecyclerMovieDBAdapter(

    var movies : List<MovieResult>?,
    private val goToDetailFragment: (MovieResult) -> Unit,
    private val toggleFavorites: (MovieResult) -> Unit) : RecyclerView.Adapter<RecyclerMovieDBAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMovieDBAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerMovieDBAdapter.ViewHolder, position: Int) {
        val movie = movies?.get(position)
        if (movie != null) {
            holder.bind(movie)
        }
        // Log.d("lista", movie.isFavorite.toString())

        holder.favoritesVT.setOnCheckedChangeListener { _, _ ->
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

/*
class RecyclerGameAdapter(val games : List<Game>) : RecyclerView.Adapter<RecyclerGameAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerGameAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerGameAdapter.ViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
        }

        override fun getItemCount(): Int {
        return games.size
        }

class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
private val title = view.findViewById<TextView>(R.id.gameTitle)
private val category = view.findViewById<TextView>(R.id.gameCategory)
private val type = view.findViewById<TextView>(R.id.gameType)
private val rate = view.findViewById<RatingBar>(R.id.gameRaiting)


        fun bind(game: Game){
        title.text = game.title
        category.text = game.category
        type.text = game.type
        rate.rating = game.rating
        }
        }

        }*/