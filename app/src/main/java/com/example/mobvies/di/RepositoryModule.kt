package com.example.mobvies.di

import com.example.mobvies.remote.repositories.MovieRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val repositoryModule: Module = module {
    single { MovieRepository(get()) }
}
