package com.example.blogappjp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.blogappjp.R
import com.example.blogappjp.core.Result
import com.example.blogappjp.core.hide
import com.example.blogappjp.core.show
import com.example.blogappjp.data.model.Post
import com.example.blogappjp.data.remote.home.HomeScreenDataSource
import com.example.blogappjp.databinding.FragmentHomeScreenBinding
import com.example.blogappjp.domain.home.HomeScreenRepoImpl
import com.example.blogappjp.presentation.HomeScreenViewModel
import com.example.blogappjp.presentation.HomeScreenViewModelFactory
import com.example.blogappjp.ui.home.adapter.HomeScreenAdapter
import com.example.blogappjp.ui.home.adapter.OnPostClickListener

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen), OnPostClickListener {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> { HomeScreenViewModelFactory(
        HomeScreenRepoImpl(
        HomeScreenDataSource()
    )
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.show()
                }

                is Result.Success -> {
                    binding.progressBar.hide()
                    if(result.data.isEmpty()) {
                        binding.emptyContainer.show()
                        return@Observer
                    }else{
                        binding.emptyContainer.hide()
                    }
                    binding.rvHome.adapter = HomeScreenAdapter(result.data, this)

                }

                is Result.Failure -> {
                    binding.progressBar.hide()
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onLikeButtonClick(post: Post, liked: Boolean) {
        viewModel.registerLikeButtonState(post.id, liked).observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    Log.d("Like Transaction", "in progress...")
                }
                is Result.Success -> {
                    Log.d("Like Transaction", "Success...")
                }
                is Result.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}