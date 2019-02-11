package com.example.mobvies.usecase

import android.content.Context
import com.example.mobvies.BASE_IMAGE_LINK
import com.example.mobvies.model.MoviesModel
import com.example.mobvies.remote.BaseMoviesResponse
import com.example.mobvies.remote.DataHolder
import com.example.mobvies.remote.enums.MovieType
import com.example.mobvies.remote.repositories.BaseUseCase
import com.example.mobvies.remote.repositories.MovieRepository
import com.example.mobvies.viewentity.MovieViewEntity
import io.reactivex.Single

class GetMoviesUseCase(
    private val context: Context,
    private val movieRepository: MovieRepository
) : BaseUseCase(context) {

    fun getMovies(
        movieType: MovieType,
        page: Int = 1,
        language: String = getLocale(),
        region: String = ""
    ): Single<DataHolder<BaseMoviesResponse<MutableList<MoviesModel>>>> {
        return movieRepository.getMovies(
            getMovieTypeByEnum(movieType),
            getApiKey(),
            page,
            language,
            region
        )

    }


    fun convertMovieModelToViewEntity(movieList: List<MoviesModel?>): MutableList<MovieViewEntity> {

        val movieViewEntityList = mutableListOf<MovieViewEntity>()

        movieList.forEach { movieModel ->
            movieModel?.let {
                if (movieModel.id != null && movieModel.posterPath != null) {
                    val movieVE =
                        MovieViewEntity(
                            movieModel.id,
                            movieModel.posterPath
                        )
                    movieViewEntityList.add(movieVE)
                }
            }
        }

        return movieViewEntityList

    }

    private fun posterPathToUri(posterPath: String?): String {
        return posterPath?.let {
            BASE_IMAGE_LINK + posterPath
        } ?: ""
    }

    private fun getMovieTypeByEnum(movieType: MovieType): String {
        return try {
            MovieType.valueOf(movieType.name).getMovieType()
        } catch (ex: Exception) {
            MovieType.POPULAR.getMovieType()
        }
    }

}