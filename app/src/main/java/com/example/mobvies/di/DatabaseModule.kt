package com.example.mobvies.di

import android.content.Context
import androidx.room.Room
import com.example.mobvies.APP_DATABASE_NAME
import com.example.mobvies.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val databaseModule: Module = module {
    single { createAppDatabase(androidContext()) }
}

fun createAppDatabase(context: Context): AppDatabase {
    return Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
}