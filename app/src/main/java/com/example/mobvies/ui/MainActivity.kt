package com.example.mobvies.ui

import android.os.Bundle
import com.example.mobvies.R
import com.example.mobvies.base.BaseActivity
import com.example.mobvies.databinding.ActivityMainBinding
import com.example.mobvies.viewmodel.MainVM

class MainActivity : BaseActivity<MainVM, ActivityMainBinding>(MainVM::class) {
    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadFragment(R.id.frmMainContainer, MoviesFragment(),true)

    }
}
