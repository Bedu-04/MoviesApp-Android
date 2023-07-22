package org.bedu.movies_app_android.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.bedu.movies_app_android.data.database.MoviesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "MOVIES_DB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, MoviesDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideFavoriteMovieDao(db: MoviesDatabase) = db.getFavoriteMovieDao()

    @Singleton
    @Provides
    fun provideNextToSeeDao(db: MoviesDatabase) = db.getNextToSeeDao()
}