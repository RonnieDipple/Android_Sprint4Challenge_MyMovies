package com.lambdaschool.datapersistencesprintchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.MovieApi
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {
    lateinit var movieApi: MovieApi

    companion object{
        val key = MovieConstants.API_KEY_PARAM
    }

    //On Failure to receive a result from MovieSearchResult a Toast is shown and a log is made in logcat
    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        Toast.makeText(this, "Request Failed", Toast.LENGTH_LONG).show()
        Log.i("Debug", t.toString())
    }

    //If MovieSearchResult responds then a check is made if successful the body is added else a log is made in logcat
    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
        if (response.isSuccessful){
            Log.i("Debug", response.body()!!.results[0].original_title)
        }else{
            Log.i("Debug", "response $response")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiates retrofit builder
        movieApi = MovieApi.MovieFactory.create()

        //Initiates the query
        button.setOnClickListener {
            val query = editText_search.toString()
            retrieveMovie(query)


        }


    }

    private fun retrieveMovie(query: String){
        movieApi.getMovies(query, key).enqueue(this)
    }




}
