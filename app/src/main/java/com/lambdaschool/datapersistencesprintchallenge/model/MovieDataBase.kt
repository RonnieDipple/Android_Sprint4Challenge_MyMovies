package com.lambdaschool.datapersistencesprintchallenge.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lambdaschool.datapersistencesprintchallenge.Dao.MovieDao

@Database(entities = [FavMovie::class], exportSchema = true, version = 1)
abstract class MovieDataBase : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    companion object {

        private var movieDataBaseInstance: MovieDataBase? = null

        fun getDataBaseInstance(context: Context): MovieDataBase? {

            if (movieDataBaseInstance == null) {
                synchronized(MovieDataBase::class) {
                    movieDataBaseInstance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDataBase::class.java, "movie_database"

                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return movieDataBaseInstance as MovieDataBase


        }
        fun destroyMovieDataBaseInstance(){
            movieDataBaseInstance = null
        }

    }


}