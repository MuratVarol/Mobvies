package com.example.mobvies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobvies.R
import com.example.mobvies.base.BaseFragment
import com.example.mobvies.databinding.FragmentMoviesBinding
import com.example.mobvies.viewmodel.MoviesVM

class MoviesFragment : BaseFragment<MoviesVM, FragmentMoviesBinding>(MoviesVM::class) {

    override val getLayoutId: Int = R.layout.fragment_movies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }


}
