package com.example.mobvies.model

import com.example.mobvies.remote.BaseMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET
    fun getMovies(
        @Url() url: String,
        @Query(value = "api_key") apiKey: String,
        @Query(value = "page") page: Int,
        @Query(value = "language") language: String,
        @Query(value = "region") region: String
    ): Single<BaseMoviesResponse<MoviesModel>>

}