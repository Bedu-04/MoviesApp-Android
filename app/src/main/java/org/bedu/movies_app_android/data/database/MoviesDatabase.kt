package org.bedu.movies_app_android.data.database

import org.bedu.movies_app_android.data.database.dao.NextToSeeMovieDao
import androidx.room.Database
import androidx.room.RoomDatabase
import org.bedu.movies_app_android.data.database.dao.FavoriteMovieDao
import org.bedu.movies_app_android.data.database.entities.FavoriteMovieEntity
import org.bedu.movies_app_android.data.database.entities.NextToSeeMovieEntity

@Database(entities = [FavoriteMovieEntity::class, NextToSeeMovieEntity::class], version = 2,)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getFavoriteMovieDao() : FavoriteMovieDao

    abstract fun getNextToSeeDao() : NextToSeeMovieDao
}