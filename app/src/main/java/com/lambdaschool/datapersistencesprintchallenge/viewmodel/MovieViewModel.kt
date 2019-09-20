package com.lambdaschool.datapersistencesprintchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MovieViewModel(application: Application): AndroidViewModel(application){

    private var movieLiveDatList: LiveData<List<>>
}