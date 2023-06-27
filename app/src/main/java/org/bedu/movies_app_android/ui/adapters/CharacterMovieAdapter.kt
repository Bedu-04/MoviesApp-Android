package org.bedu.movies_app_android.ui.adapters

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
import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.MovieResult

class RecyclerCharacterMovieAdapter(
    private var cast : List<Cast>,
) : RecyclerView.Adapter<RecyclerCharacterMovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = cast[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val characterName = view.findViewById<TextView>(R.id.character_name)
        private val perfomerName = view.findViewById<TextView>(R.id.performer_name)
        private val imagePerformer = view.findViewById<ImageView>(R.id.img)

        fun bind(movie: Cast) {
            characterName.text = movie.character
            perfomerName.text = movie.name
            Picasso.get().load("https://image.tmdb.org/t/p/w300" + movie.profile_path).into(imagePerformer);
        }

    }
}