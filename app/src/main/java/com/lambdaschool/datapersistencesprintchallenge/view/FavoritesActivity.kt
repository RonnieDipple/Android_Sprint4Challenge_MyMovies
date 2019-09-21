package com.lambdaschool.datapersistencesprintchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieApi
import com.lambdaschool.datapersistencesprintchallenge.model.FavMovie
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import kotlinx.android.synthetic.main.activity_main.*
//Deals with favoriting movies
class FavoritesActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel
    lateinit var favsList: List<FavMovie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)


        //copied over from MainActivity
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        favsList = movieViewModel.getListOfMovies()//gets list of movies from view model
        addViews(favsList)

    }

    private fun buildItemView(movie: FavMovie /*forgot this needs to be favmovie*/): TextView {
        var view = TextView(this)
        view.text = movie.movieTitle
        view.textSize = 34f
        val watched = resources.getColor(R.color.colorAccent)//why is this depreciated investigate for build week
        val notWatched = resources.getColor(R.color.colorPrimary)
        if (movie.movieWatched == true){
            view.setBackgroundColor(watched)
        }else{
            view.setBackgroundColor(notWatched)
        }

        //New view needs an onclick listener
        view.setOnClickListener {
            //Logic for view
            if (movie.movieWatched == false){
                movie.movieWatched = true //the watched toggle spoked about in the readme toggled to true
                movieViewModel.updateMovie(movie)//updates movie in movieViewModel
                view.setBackgroundColor(watched)//changes background color
            }else{
                movie.movieWatched = false //the opposite of above
                movieViewModel.updateMovie(movie)//updates movie in movieViewModel
                view.setBackgroundColor(notWatched)//changes background color


            }
        }

        view.setOnDragListener {

            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setMessage("Do you really want to remove the movie from your favorites?")
            //how do they respond?

            true
        }

        return view
    }

    //This will add the views created above to the Linear Layout
    fun addViews(movie: List<FavMovie/*forgot this needs to be favmovie*/>) {
        //If it wasn't for Brandon telling me about this the other day I wouldn't have thought of removing all the views
        // I have no idea why I can't remember things, forEach doesn't work with live data? I am lost
        linear_layout_list.removeAllViews()
        movie.forEach { movie ->
            linear_layout_list.addView(buildItemView(movie))

            //this should work


        }

        //I am getting tired
    }


}
