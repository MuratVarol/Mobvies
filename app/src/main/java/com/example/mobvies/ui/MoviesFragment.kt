package com.example.mobvies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobvies.R
import com.example.mobvies.base.BaseFragment
import com.example.mobvies.databinding.FragmentMoviesBinding
import com.example.mobvies.util.listener.EndlessRecyclerViewScrollListener
import com.example.mobvies.viewmodel.MoviesVM
import observe

class MoviesFragment : BaseFragment<MoviesVM, FragmentMoviesBinding>(MoviesVM::class) {

    override val getLayoutId: Int = R.layout.fragment_movies

    private val visibleThreshold = 3

    lateinit var endlessRecyclerViewScrollListener : EndlessRecyclerViewScrollListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        subscribeResetScrollState()

        return binding.root
    }

    private fun subscribeResetScrollState() {
        viewModel.isNeedToResetScrollState.observe(this) {
            if (it == true)
                endlessRecyclerViewScrollListener.resetState()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewListeners()
    }

    private fun setRecyclerViewListeners() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.rvMovies.layoutManager = gridLayoutManager
        endlessRecyclerViewScrollListener= object :
            EndlessRecyclerViewScrollListener(gridLayoutManager, visibleThreshold) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getPopularMovies(page.plus(1))
            }
        }

        binding.rvMovies.addOnScrollListener(endlessRecyclerViewScrollListener)

        binding.srl.setOnRefreshListener {
            viewModel.getPopularMovies(1)
        }

    }


}
