package com.example.mobvies.di

import com.example.mobvies.usecase.GetMoviesUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val useCaseModule: Module = module {
    single { GetMoviesUseCase(get(), get()) }
}

