package com.example.mobvies.remote.repositories

import android.content.Context
import com.example.mobvies.model.Api
import com.example.mobvies.model.MoviesModel
import com.example.mobvies.remote.BaseMoviesResponse
import com.example.mobvies.remote.DataHolder
import com.example.mobvies.remote.enums.MovieType
import io.reactivex.Single

class MovieRepository(
    private val api: Api
) : BaseRepository() {

    fun getMovies(
        movieType: String,
        apiKey: String,
        page: Int = 1,
        language: String,
        region: String = ""
    ): Single<DataHolder<BaseMoviesResponse<MoviesModel>>> {
        return service.sendRequest(
            api.getMovies(
                movieType,
                apiKey,
                page,
                language,
                region
            )
        )
    }



}