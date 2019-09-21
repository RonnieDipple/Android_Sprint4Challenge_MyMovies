package com.lambdaschool.datapersistencesprintchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.model.FavMovie
import com.lambdaschool.datapersistencesprintchallenge.repository.MovieRepository

class MovieViewModel(application: Application): AndroidViewModel(application){

//The view model this will hurt but not as much as the repo
    private var movieRepo: MovieRepository = MovieRepository(application) //this was a headache

    private var movieLiveDatList: LiveData<List<FavMovie>> = movieRepo.getAllMovies() //Omg I fill in the whole repo and this goes red yay// Solved

    fun  insertMovie(movie: FavMovie){
        //calls the repo insert function
        movieRepo.insertMovie(movie)
    }

    fun  deleteMovie(movie: FavMovie){
        //calls the repo delete function
        movieRepo.deleteMovie(movie)
    }

    fun  updateMovie(movie: FavMovie){
        //calls the repo update function
        movieRepo.updateMovie(movie)
    }

    //gets live list of all movies
    fun getListOfMovies(): LiveData<List<FavMovie>>{
        var list: LiveData<List<FavMovie>> = movieRepo.getAllMovies()
        return list
    }

}