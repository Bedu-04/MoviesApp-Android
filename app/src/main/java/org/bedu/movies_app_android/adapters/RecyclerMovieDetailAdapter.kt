package org.bedu.movies_app_android.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.models.Movie
import org.bedu.movies_app_android.R


class RecyclerMovieDetailAdapter(private val movies : List<Movie>) : RecyclerView.Adapter<RecyclerMovieDetailAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val titleVT = view.findViewById<TextView>(R.id.movie_title)
        private val actorsVT = view.findViewById<TextView>(R.id.movie_actors)
        private val directorVT = view.findViewById<TextView>(R.id.movie_director)
        // private val category = view.findViewById<TextView>(R.id.movie)
        private val languageVT = view.findViewById<TextView>(R.id.movie_language)
        private val durationVT = view.findViewById<TextView>(R.id.movie_duration)
        // private val rate = view.findViewById<RatingBar>(R.id.movie_rating)
        private val ratingVT = view.findViewById<TextView>(R.id.movie_rating)
        private val dateVT = view.findViewById<TextView>(R.id.movie_date)
        private val resumeTV = view.findViewById<TextView>(R.id.movie_resume)
        private val imageIV = view.findViewById<ImageView>(R.id.movie_poster)


        fun bind(movie: Movie) {
            titleVT.text = movie.name
            actorsVT.text = movie.actors.joinToString(limit = 3, truncated = "....") { it ->
                it.replaceFirstChar{it -> it.uppercase()}
            }
            directorVT.text = movie.directors.joinToString(limit = 1, truncated = "....") {
                it.replaceFirstChar{it -> it.uppercase()}
            }
            // category.text = movie.category.toString()
            languageVT.text = movie.language.toString()
            durationVT.text = "${movie.duration.toString()} min"
            ratingVT.text = movie.rating.toString()
            resumeTV.text = movie.resume.take(60) + "..."
            dateVT.text = movie.date
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
