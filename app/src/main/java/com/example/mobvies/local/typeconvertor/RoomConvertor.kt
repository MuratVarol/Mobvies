package com.example.mobvies.local.typeconvertor

import androidx.room.TypeConverter
import com.example.mobvies.model.MoviesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConvertor {

    private val gson = Gson()

    @TypeConverter
    fun fromMovieList(data: List<MoviesModel>): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toMovieList(json: String): List<MoviesModel> {
        val listType = object : TypeToken<List<MoviesModel>>() {}.type
        return gson.fromJson(json, listType)
    }


}