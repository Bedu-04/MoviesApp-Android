package org.bedu.movies_app_android.data.database

import android.content.Context
import org.bedu.movies_app_android.data.database.dao.NextToSeeMovieDao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.bedu.movies_app_android.data.database.dao.FavoriteMovieDao
import org.bedu.movies_app_android.data.database.entities.FavoriteMovieEntity
import org.bedu.movies_app_android.data.database.entities.NextToSeeMovieEntity

@Database(entities = [FavoriteMovieEntity::class, NextToSeeMovieEntity::class], version = 4)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getFavoriteMovieDao() : FavoriteMovieDao

    abstract fun getNextToSeeDao() : NextToSeeMovieDao

    companion object {
        fun getInstance(context: Context): MoviesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                "MOVIES_DB"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}