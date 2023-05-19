package org.bedu.movies_app_android.adapters

import android.media.Rating
import android.util.Log
import org.bedu.movies_app_android.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.models.Movie


class RecyclerFavoritesAdapter(
    private val movies : MutableList<Movie>) : RecyclerView.Adapter<RecyclerFavoritesAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerFavoritesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerFavoritesAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        // holder.itemView.setOnClickListener{clickListener(movie)}
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val favoritesVT = view.findViewById<CheckBox>(R.id.cbHeart)
        private val durationVT = view.findViewById<TextView>(R.id.movie_duration)
        private val ratingVT = view.findViewById<RatingBar>(R.id.movie_rating)
        private val imageIV = view.findViewById<ImageView>(R.id.img)

        fun bind(movie: Movie) {

            favoritesVT.isChecked = true
            durationVT.text = "${movie.duration.toString()} min"
            ratingVT.rating = movie.rating.toFloat()
            imageIV.setImageResource(movie.image)
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