package com.example.mobvies.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.mobvies.base.BaseVM
import com.example.mobvies.model.MoviesModel
import com.example.mobvies.remote.DataHolder
import com.example.mobvies.remote.enums.MovieType
import com.example.mobvies.usecase.GetMoviesUseCase
import com.example.mobvies.util.SingleLiveEvent
import com.example.mobvies.util.listener.ItemClickListener
import plusAssign

class MoviesVM(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseVM() {

    val popularMovieViewEntityList = MutableLiveData<MutableList<MoviesModel>>()
    val upcomingMovieViewEntityList = MutableLiveData<MutableList<MoviesModel>>()
    val latestMovieViewEntityList = MutableLiveData<MutableList<MoviesModel>>()

    val isLoading = SingleLiveEvent<Boolean>()
    val isRefreshing = SingleLiveEvent<Boolean>()


    val isNeedToResetScrollState = SingleLiveEvent<Boolean>()

    val selectedMovie = SingleLiveEvent<MoviesModel>()

    val itemClickListener = object : ItemClickListener<MoviesModel> {
        override fun onItemClick(view: View, item: MoviesModel, position: Int) {
            selectedMovie.postValue(item)
        }

    }



    init {
        getPopularMovies(1)
    }


    fun getPopularMovies(page: Int) {
        getMovies(MovieType.POPULAR, page)
    }

    fun getUpcomingMovies(page: Int) {
        getMovies(MovieType.UPCOMING, page)
    }

    fun getLatestMovies(page: Int) {
        getMovies(MovieType.LATEST, page)
    }

    private fun getMovies(movieType: MovieType, page: Int) {

        isLoading.postValue(true)

        val disposable = getMoviesUseCase
            .getMovies(movieType, page)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getMainThreadScheduler())
            .subscribe { data ->
                isLoading.postValue(false)
                isRefreshing.postValue(false)
                when (data) {

                    is DataHolder.Success -> {

                        if (page == 1){
                            isNeedToResetScrollState.postValue(true)

                            when (movieType) {
                                MovieType.POPULAR -> popularMovieViewEntityList.postValue(data.data.results)
                                MovieType.LATEST -> latestMovieViewEntityList.postValue(data.data.results)
                                MovieType.UPCOMING -> upcomingMovieViewEntityList.postValue(data.data.results)
                            }

                        }else{


                            when (movieType) {
                                MovieType.POPULAR -> popularMovieViewEntityList += data.data.results
                                MovieType.LATEST -> latestMovieViewEntityList += data.data.results
                                MovieType.UPCOMING -> upcomingMovieViewEntityList += data.data.results
                            }
                        }

                    }
                }
            }

        disposables.add(disposable)
    }

}