package com.example.mobvies.di

import com.example.mobvies.viewmodel.MoviesVM
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { MoviesVM(get()) }
}