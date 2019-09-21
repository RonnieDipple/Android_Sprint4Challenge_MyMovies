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
import kotlinx.android.synthetic.main.activity_favorites.*
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
        var myView = TextView(this)
        myView.text = movie.movieTitle
        myView.textSize = 34f
        val watched = resources.getColor(R.color.colorAccent)//why is this depreciated investigate for build week
        val notWatched = resources.getColor(R.color.colorPrimary)
        if (movie.movieWatched){
            myView.setBackgroundColor(watched)
        }else{
            myView.setBackgroundColor(notWatched)
        }

        //New view needs an onclick listener
        myView.setOnClickListener {
            //Logic for view
            if (!movie.movieWatched){
                movie.movieWatched = true //the watched toggle spoked about in the readme toggled to true
                movieViewModel.updateMovie(movie)//updates movie in movieViewModel
                myView.setBackgroundColor(watched)//changes background color
            }else{
                movie.movieWatched = false //the opposite of above
                movieViewModel.updateMovie(movie)//updates movie in movieViewModel
                myView.setBackgroundColor(notWatched)//changes background color


            }
        }

        myView.setOnLongClickListener {

            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setMessage("Do you really want to remove the movie from your favorites?")
            //how do they respond?

           /* alertBuilder.setIcon(R.drawable.ic_sentiment_satisfied_black_24dp) not working */
            alertBuilder.setPositiveButton("YES"){ dialog, which ->
                movieViewModel.deleteMovie(movie)
                favsList = movieViewModel.getListOfMovies()
                addViews(favsList)
            }

           alertBuilder.setNegativeButton("NO"){dialog, which ->


            }

            val dialog: AlertDialog = alertBuilder.create()
            dialog.show()

            true

            /*This is not working Google doc for setOnDragListener Returns true if the drag event was handled successfully, or false if the drag event was not handled.
            Note that false will trigger the View to call its onDragEvent() handler.*/
        }

        return myView


    }

    //This will add the views created above to the Linear Layout
    fun addViews(movie: List<FavMovie>) {
        linear_layout_list_fav.removeAllViews()
        movie.forEach { movie ->
            linear_layout_list_fav.addView(buildItemView(movie))
            //this should work
        }
        //I am getting tired
    }


}
