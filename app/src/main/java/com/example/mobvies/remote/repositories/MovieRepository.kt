package com.example.mobvies.remote.repositories

import com.example.mobvies.remote.Api
import com.example.mobvies.model.MoviesModel
import com.example.mobvies.remote.BaseMoviesResponse
import com.example.mobvies.remote.DataHolder
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
    ): Single<DataHolder<BaseMoviesResponse<List<MoviesModel>>>> {
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