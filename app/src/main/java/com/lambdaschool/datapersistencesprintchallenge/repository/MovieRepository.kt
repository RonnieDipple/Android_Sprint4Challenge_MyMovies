package com.lambdaschool.datapersistencesprintchallenge.repository

import android.content.Context
import android.os.AsyncTask
import com.lambdaschool.datapersistencesprintchallenge.Dao.MovieDao
import com.lambdaschool.datapersistencesprintchallenge.model.FavMovie
import com.lambdaschool.datapersistencesprintchallenge.model.MovieDataBase


//I have no idea what to do here my mind is going blank
class MovieRepository(context: Context) {

    //List of fav movie
     var movieDao: MovieDao

    init {
        //Initialize all the things
        val movieDataBase: MovieDataBase = MovieDataBase.getDataBaseInstance(context)!!
        movieDao = movieDataBase.movieDao()

    }


    //gets all the fav movies
    fun getAllMovies():List<FavMovie>{
        return MovieASyncTask(movieDao).execute().get()

    }

    //insets fav movie into database
    fun insertMovie(movie: FavMovie){
        InsertAsyncTask(movieDao).execute(movie)

    }

    //Updates the favmovie
    fun updateMovie(movie: FavMovie){
        UpdateMovieAsyncTask(movieDao).execute(movie)

    }

    //deletes the fav movie
    fun deleteMovie(movie: FavMovie){
        DeleteMovieASyncTask(movieDao).execute(movie)
    }

    companion object {
        //uses asynctask for the insert fun above
        private class InsertAsyncTask(movieDao: MovieDao) : AsyncTask<FavMovie, Unit, Unit>() {
            val MovieDao = movieDao

            override fun doInBackground(vararg params: FavMovie?) {
                MovieDao.Insert(params[0]!!)

            }
        }


        //I hate this jumping backwards and forwards

        //uses asynctask for the update fun above
        private class UpdateMovieAsyncTask(movieDao: MovieDao) : AsyncTask<FavMovie, Unit, Unit>() {
            val MovieDao = movieDao //hate how you have to essential reverse this it triggers me
            override fun doInBackground(vararg params: FavMovie?) {
                MovieDao.update(params[0]!!)

            }
        }

        //uses asynctask for the delete fun above
        private class DeleteMovieASyncTask(movieDao: MovieDao) : AsyncTask<FavMovie, Unit, Unit>() {
            val MovieDao = movieDao //triggered
            override fun doInBackground(vararg params: FavMovie?) {
                MovieDao.delete(params[0]!!)
            }
        }


        // The task that caused me pain
        private class MovieASyncTask(movieDao: MovieDao) :
            AsyncTask<FavMovie, Unit, List<FavMovie>>() {
            val MovieDao = movieDao


            override fun doInBackground(vararg params: FavMovie?): List<FavMovie> {
                val list = MovieDao.getAllMovies()
                return list

            }

        }
    }
}