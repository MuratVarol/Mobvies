package com.example.mobvies

import android.app.Application
import com.example.mobvies.di.*
import org.koin.android.ext.android.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                networkModule,
                databaseModule,
                viewModelModule,
                useCaseModule,
                repositoryModule
            )
        )
    }


}