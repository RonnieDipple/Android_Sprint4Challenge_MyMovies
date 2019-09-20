package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
class FavMovie(

    val movieTitle: String,
    val movieDate: String,
    var moveWatched: Boolean,
    var moveisFav: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
