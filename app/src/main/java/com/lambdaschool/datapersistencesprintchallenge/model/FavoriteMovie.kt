package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_movie")
data class FavoriteMovie(
    @PrimaryKey @ColumnInfo(name = "movie_title")
    val movieTitle: String?,
    var isFavorite: Boolean,
    var isWatched: Boolean

)