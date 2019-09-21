package com.lambdaschool.datapersistencesprintchallenge.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.datapersistencesprintchallenge.model.FavMovie
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview


@Dao
interface MovieDao{

   @Query("SELECT * FROM movie_table") //this was called movies until I realized I made a huge mistake now it is a table, well it was meant to be a table at the start
    fun getAllMovies(): List<FavMovie>
/*
    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun getMovieId(movieId: Int):MovieOverview?

    @Query("SELECT movie_id FROM movies")
    fun getALLIds():List<Long>*/

    //Insert, Delete and Update here will be inherited by daos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Insert(movie: FavMovie): Long // what to do incase of conflict? insert the t is what it does!

    @Delete
    fun delete(movie: FavMovie) //Delete the book etc

    @Update
    fun update(movie: FavMovie) //update the book etc
//If I don't comment like this I will look at my code and forget how it works


}