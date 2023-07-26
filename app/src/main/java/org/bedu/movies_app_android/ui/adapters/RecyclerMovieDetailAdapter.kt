package org.bedu.movies_app_android.ui.adapters
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieResult


class RecyclerMovieDetailAdapter(var movieSelected : List<MovieResult>, var actors : List<Cast>, var director: Crew) : RecyclerView.Adapter<RecyclerMovieDetailAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerCinemaListing)

        // recyclerView.layoutManager = LinearLayoutManager(, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RecyclerCharacterMovieAdapter(actors)
        recyclerView.layoutManager = GridLayoutManager(view.context, 3)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieSelected[position]
        holder.bind(movie, director)
    }

    override fun getItemCount(): Int {
        Log.d("ACTORS", actors.toString())
        return movieSelected.size

    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val titleDetail = view.findViewById<TextView>(R.id.movie_title)
        private val actorsDetail = view.findViewById<TextView>(R.id.movie_actors)
        private val directorDetail = view.findViewById<TextView>(R.id.movie_director)
        // private val category = view.findViewById<TextView>(R.id.movie)
        private val directorName = view.findViewById<TextView>(R.id.director_name)
        private val directorImage = view.findViewById<ImageView>(R.id.director_image)
        // private val ratingDetail = view.findViewById<RatingBar>(R.id.movie_rating)
        private val ratingDetail = view.findViewById<TextView>(R.id.movie_rating)

        private val dateDetail = view.findViewById<TextView>(R.id.movie_date)
        private val resumeDetail = view.findViewById<TextView>(R.id.movie_resume)
        private val imageDetail = view.findViewById<ImageView>(R.id.movie_poster)




        fun bind(movie: MovieResult, director: Crew) {
            titleDetail.text = movie.title
           /* actorsDetail.text = movie.a.joinToString(", ") { it ->
                it.replaceFirstChar{it -> it.uppercase()}
            }
            directorDetail.text = movie.directors.joinToString(limit = 1, truncated = "....") {
                it.replaceFirstChar{it -> it.uppercase()}
            }*/
            // languageDetail.text = movie.original_language
            // durationDetail.text = "${movie.duration.toString()} min"
            directorName.text = director.name
            ratingDetail.text = "${movie.vote_average.toString()} /10"
            resumeDetail.text = movie.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + director.profile_path).into(directorImage);
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + movie.poster_path).into(imageDetail);

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
