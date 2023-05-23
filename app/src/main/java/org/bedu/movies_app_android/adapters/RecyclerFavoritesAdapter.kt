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
import org.bedu.movies_app_android.models.Movie


class RecyclerFavoritesAdapter(
    private val movies: MutableList<Movie>,
    private val goToDetailFragment: (Movie) -> Unit,
    private val toggleFavorites: (Movie) -> Unit
) : RecyclerView.Adapter<RecyclerFavoritesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerFavoritesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerFavoritesAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

        holder.favorites_btn.isChecked = true

        holder.favorites_btn.setOnCheckedChangeListener { _, _ ->
            toggleFavorites(movie)
        }
        holder.itemView.setOnClickListener{goToDetailFragment(movie)}
    }


    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val favorites_btn = view.findViewById<CheckBox>(R.id.cbHeart)
        private val rating_bar = view.findViewById<RatingBar>(R.id.ratingBar_catalog)
        private val image_background = view.findViewById<ImageView>(R.id.img)
        private val title = view.findViewById<TextView>(R.id.title_catalog)


        fun bind(movie: Movie) {
            title.text = movie.name
            favorites_btn.isChecked = movie.isFavorite
            rating_bar.rating = movie.rating.toFloat()
            image_background.setImageResource(movie.image)
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