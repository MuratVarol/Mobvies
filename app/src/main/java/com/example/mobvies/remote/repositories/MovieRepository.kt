package com.example.mobvies.remote.repositories

import android.content.Context
import com.example.mobvies.model.Api
import com.example.mobvies.model.MoviesModel
import com.example.mobvies.remote.BaseMoviesResponse
import com.example.mobvies.remote.DataHolder
import com.example.mobvies.remote.enums.MovieType
import io.reactivex.Single

class MovieRepository(
    private val context: Context,
    private val api: Api
) : BaseRepository(context) {

    fun getPopularMovies(
        movieType: MovieType,
        page: Int = 1,
        language: String = getLocale(),
        region: String = ""
    ): Single<DataHolder<BaseMoviesResponse<MoviesModel>>> {
        return service.sendRequest(
            api.getMovies(
                getMovieTypeByEnum(movieType),
                getApiKey(),
                page,
                language,
                region
            )
        )
    }

    private fun getMovieTypeByEnum(movieType: MovieType): String {
        return try {
            MovieType.valueOf(movieType.name).getMovieType()
        } catch (ex: Exception) {
            MovieType.POPULAR.getMovieType()
        }
    }

}