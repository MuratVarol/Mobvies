package com.example.mobvies.model

import com.example.mobvies.remote.BaseMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("popular")
    fun getPopularMovies(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "language") language: String,
        @Query(value = "page") page: String,
        @Query(value = "region") region: String
    ): Single<BaseMoviesResponse<MoviesModel>>

    @GET("latest")
    fun getLatestMovies(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "language") language: String,
        @Query(value = "page") page: String,
        @Query(value = "region") region: String
    ): Single<BaseMoviesResponse<MoviesModel>>

    @GET("upcoming")
    fun getUpcomingMovies(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "language") language: String,
        @Query(value = "page") page: String,
        @Query(value = "region") region: String
    ): Single<BaseMoviesResponse<MoviesModel>>

}