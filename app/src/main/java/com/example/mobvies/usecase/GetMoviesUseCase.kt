package com.example.mobvies.usecase

import android.content.Context
import com.example.mobvies.remote.enums.MovieType
import com.example.mobvies.remote.repositories.BaseUseCase
import com.example.mobvies.remote.repositories.MovieRepository

class GetMoviesUseCase(
    private val context: Context,
    private val movieRepository: MovieRepository
) : BaseUseCase(context) {

    fun getMovies(
        movieType: MovieType,
        page: Int = 1,
        language: String = getLocale(),
        region: String = ""
    ) {
        movieRepository.getMovies(
            getMovieTypeByEnum(movieType),
            getApiKey(),
            page,
            language,
            region
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