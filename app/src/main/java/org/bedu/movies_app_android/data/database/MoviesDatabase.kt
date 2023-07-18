package org.bedu.movies_app_android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.bedu.movies_app_android.data.database.dao.FavoriteMovieDao
import org.bedu.movies_app_android.data.database.entities.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getFavoriteMovieDao() : FavoriteMovieDao
}