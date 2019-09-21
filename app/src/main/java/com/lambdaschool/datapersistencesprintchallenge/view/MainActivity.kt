package com.lambdaschool.datapersistencesprintchallenge.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieApi
import com.lambdaschool.datapersistencesprintchallenge.model.FavMovie
import com.lambdaschool.datapersistencesprintchallenge.model.MovieDataBase
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {
    lateinit var movieApi: MovieApi
    lateinit var favMovie: FavMovie
    lateinit var movieSearchResult: MovieSearchResult
    lateinit var movieViewModel: MovieViewModel
    lateinit var movieDataBase: MovieDataBase //Lateinit varing all these, can't get anything to display right now

    companion object {
        val key = MovieConstants.API_KEY_PARAM
    }

    //On Failure to receive a result from MovieSearchResult a Toast is shown and a log is made in logcat
    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        Toast.makeText(this, "Request Failed", Toast.LENGTH_LONG).show()
        Log.i("Debug", t.toString())
    }

    //If MovieSearchResult responds then a check is made if successful the body is added else a log is made in logcat
    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {

        //omg if forgot to do anything worthwhile here I just had it doing debug responses
        if (response.isSuccessful) {
            Log.i("Debug", response.body()!!.results[0].original_title)
            movieSearchResult = response.body() as MovieSearchResult
            addViews(movieSearchResult.results)//this should show the movie search result,
            // it does this by displaying the response body of moviesearchresult and passing that into the addview fun which is down below
            // that intern should populate the linear layout in
            // scrollview in the mainActivity xml
        } else {
            Log.i("Debug", "response $response")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiates retrofit builder
        movieApi = MovieApi.MovieFactory.create()

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        //Initiates the query
        imageView.setOnClickListener {
            val query = editText_search.text.toString() //I forgot to put text here kill me
            retrieveMovie(query)


        }

        button.setOnClickListener {

            //Starts activity
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }


    //Need to build the views I can make these no problem shame about my other code, oh I was wondering why movies wasn't showing up
    // turns out I am an idiot and didn't even populate my layout, kill me
    private fun buildItemView(movie: MovieOverview/*is this right?*/): TextView {
        var view = TextView(this)
        view.text = movie.original_title
        view.textSize = 34f//Tried 50f, it was way to big
        //This onclicklistener passes data to favorite movie, can do some funky things here like change view color or other things, worth experimenting
        view.setOnClickListener {
            //need to implement some logic here maybe is favorite, maybe I don't need isFav
            val favMovie = FavMovie(movie.original_title, movie.original_language, false)
            val backgroundColor = R.color.colorAccent
            view.resources.getColor(backgroundColor)//Changes background color
            movieViewModel.insertMovie(favMovie)

        }

        return view
    }

    //This will add the views created above to the Linear Layout
    fun addViews(movie: List<MovieOverview>) {
        //If it wasn't for Brandon telling me about this the other day I wouldn't have thought of removing all the views
        // I have no idea why I can't remember things, forEach doesn't work with live data? I am lost
        linear_layout_list.removeAllViews()
        movie.forEach { movie ->
            linear_layout_list.addView(buildItemView(movie))

            //this should work


        }

        //I am getting tired
    }


    fun retrieveMovie(query: String) {
        movieApi.getMovies(query, key).enqueue(this)
    }

}



