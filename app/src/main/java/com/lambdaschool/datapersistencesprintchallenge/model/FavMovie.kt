package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey


//Table for fav Sqllite
@Entity(tableName = "movie_table")
class FavMovie(

    val movieTitle: String,
    val movieDate: String,
    var movieWatched: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
