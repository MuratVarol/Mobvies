package com.example.mobvies.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mobvies.model.MoviesModel
import io.reactivex.Maybe

@Dao
interface MoviesDao : BaseDao<MoviesModel> {

    @Query("SELECT * FROM movieModel")
    fun getPopularMovies(): Maybe<List<MoviesModel>>

}