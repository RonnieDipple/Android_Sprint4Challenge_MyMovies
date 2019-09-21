package com.lambdaschool.datapersistencesprintchallenge.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lambdaschool.datapersistencesprintchallenge.Dao.MovieDao

//Hands stuff over to wonderful room to deal with kind of
@Database(entities = [FavMovie::class], exportSchema = true, version = 2)
abstract class MovieDataBase : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    companion object {

        private var movieDataBaseInstance: MovieDataBase? = null

        fun getDataBaseInstance(context: Context): MovieDataBase? {


            if (movieDataBaseInstance == null) {

                //synchronized Executes the given function [block] while holding the monitor of the given object [lock].
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


    }


}