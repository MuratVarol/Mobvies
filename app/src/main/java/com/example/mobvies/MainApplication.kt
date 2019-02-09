package com.example.mobvies

import android.app.Application
import com.example.mobvies.di.databaseModule
import com.example.mobvies.di.networkModule
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
                databaseModule
            )
        )
    }


}