package com.lambdaschool.datapersistencesprintchallenge.Dao

import androidx.room.Dao
import androidx.room.Query
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview

@Dao
interface MovieDao: BaseDao<MovieOverview>{

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieOverview>

    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun getMovieId(movieId: Int):MovieOverview?

    @Query("SELECT movie_id FROM movies")
    fun getALLIds():List<Long>


}