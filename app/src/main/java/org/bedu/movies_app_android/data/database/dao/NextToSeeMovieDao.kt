package org.bedu.movies_app_android.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.bedu.movies_app_android.data.database.entities.NextToSeeMovieEntity


@Dao
interface NextToSeeMovieDao {
    @Query("SELECT * FROM favorite_movies ORDER BY original_title DESC")
    suspend fun getAll(): List<NextToSeeMovieEntity>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getById(movieId: Int): NextToSeeMovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(favoriteMovie: NextToSeeMovieEntity)

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteById(movieId: Int): Int

}