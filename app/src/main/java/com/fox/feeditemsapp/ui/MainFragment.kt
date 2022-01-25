package com.fox.feeditemsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fox.feeditemsapp.adapter.MainAdapter
import com.fox.feeditemsapp.databinding.FragmentMainBinding
import com.fox.feeditemsapp.utils.ResponseState
import com.fox.feeditemsapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeContainer: SwipeRefreshLayout
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        progressBar = binding.progressBarMain
        swipeContainer = binding.swipeContainer
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        swipeContainer.setOnRefreshListener {
            getFeedItems()
        }

        getFeedItems()

        return binding.root
    }

    private fun getFeedItems() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.feedItemStateFlow.also {
                mainViewModel.getFeedItems()
            }
            .collectLatest {
                when (it) {
                    is ResponseState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                    is ResponseState.Success -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        swipeContainer.isRefreshing = false
                        adapter.setData(it.data)
                    }
                    is ResponseState.Error -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}