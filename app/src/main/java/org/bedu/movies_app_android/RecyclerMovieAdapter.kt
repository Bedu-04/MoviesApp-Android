package org.bedu.movies_app_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerMovieAdapter(private val movies : List<Movie>) : RecyclerView.Adapter<RecyclerMovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerMovieAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val category = view.findViewById<TextView>(R.id.category)
        private val language = view.findViewById<TextView>(R.id.language)
        private val duration = view.findViewById<TextView>(R.id.duration)
        private val rate = view.findViewById<RatingBar>(R.id.ratingBar)


        fun bind(movie: Movie) {
            title.text = movie.name
            category.text = movie.category.toString()
            language.text = movie.language.toString()
            duration.text = movie.duration.toString()
            rate.rating = movie.rating.toFloat()
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
