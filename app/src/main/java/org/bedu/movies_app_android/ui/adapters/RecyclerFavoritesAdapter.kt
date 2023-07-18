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
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.domain.model.Movie


class RecyclerFavoritesAdapter(
    var movies: MutableList<Movie>,
    private val goToDetailFragment: (Movie) -> Unit,
    private val toggleFavorites: (Movie, Boolean) -> Unit,
) : RecyclerView.Adapter<RecyclerFavoritesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)


        holder.favoriteCheck.setOnClickListener {
            Log.d("TAG", "ME EJECUTO en el click")
            val isInsertOperation = holder.favoriteCheck.isChecked
            toggleFavorites(movie, isInsertOperation)
        }

        holder.itemView.setOnClickListener{goToDetailFragment(movie)}
    }


    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val favoriteCheck = view.findViewById<CheckBox>(R.id.cbHeart)
        private val rating_bar = view.findViewById<RatingBar>(R.id.ratingBar_catalog)
        private val image_background = view.findViewById<ImageView>(R.id.img)
        private val title = view.findViewById<TextView>(R.id.title_catalog)


        fun bind(movie: Movie) {
            Log.d("MOVIE CHECK", movie.isFavorite.toString())
            title.text = movie.title
            favoriteCheck.isChecked = movie.isFavorite
            rating_bar.rating = movie.vote_average.toFloat()
            /*image_background.setImageResource(movie.image)*/
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + movie.poster_path).into(image_background);
        }
    }
}
