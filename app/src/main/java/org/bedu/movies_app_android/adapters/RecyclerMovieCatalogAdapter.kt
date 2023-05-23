package org.bedu.movies_app_android

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.models.Movie


class RecyclerMovieCatalogAdapter(

    private val movies : MutableList<Movie>,
    private val goToDetailFragment: (Movie) -> Unit,
    private val toggleFavorites: (Movie) -> Unit) : RecyclerView.Adapter<RecyclerMovieCatalogAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMovieCatalogAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerMovieCatalogAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        Log.d("lista", movie.isFavorite.toString())

        holder.favoritesVT.setOnCheckedChangeListener { _, _ ->
           toggleFavorites(movie)
        }

        holder.itemView.setOnClickListener{goToDetailFragment(movie)}
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val titleVT = view.findViewById<TextView>(R.id.title_catalog)
        private val ratingBarVT = view.findViewById<RatingBar>(R.id.ratingBar_catalog)
        private val imageIV = view.findViewById<ImageView>(R.id.img)
        val favoritesVT = view.findViewById<CheckBox>(R.id.cbHeart)

        fun bind(movie: Movie) {
            titleVT.text = movie.name.toString()
            ratingBarVT.rating = movie.rating.toFloat()
            imageIV.setImageResource(movie.image)
            favoritesVT.isChecked = movie.isFavorite
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