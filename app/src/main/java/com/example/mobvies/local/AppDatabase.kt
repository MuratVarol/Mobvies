package com.example.mobvies.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobvies.BuildConfig
import com.example.mobvies.local.dao.MoviesDao
import com.example.mobvies.local.typeconvertor.RoomConvertor
import com.example.mobvies.model.MoviesModel

@Database(
    entities = [MoviesModel::class], version = BuildConfig.VERSION_CODE, exportSchema = false
)
@TypeConverters(RoomConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}