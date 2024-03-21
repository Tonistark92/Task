package com.iscoding.mytask.presentation.screens.detailsScreen

import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iscoding.mytask.R
import com.iscoding.mytask.databinding.FragmentAllPostsBinding
import com.iscoding.mytask.databinding.FragmentDetailsBinding
import com.iscoding.mytask.presentation.screens.allpostsscreen.AllPostsFragmentDirections
import com.iscoding.mytask.presentation.screens.allpostsscreen.adapter.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()
    val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = args.postId
        detailsViewModel.setId(id)
        detailsViewModel.getPost()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch {
            detailsViewModel.state.collect { state ->
                if (state.isLoading) {
                    // Show progress bar when loading
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("ISLAM","LOADING  is ${state.isLoading}")
                } else {
                    // Hide progress bar when not loading
                    binding.progressBar.visibility = View.GONE
                    Log.d("ISLAM","LOADING  is ${state.isLoading}")
                    binding.tvTitleBody.text= detailsViewModel.state.value.post?.title
                    binding.tvItemBody.text = detailsViewModel.state.value.post?.body
                    Log.d("DATA" ,"fragment ${detailsViewModel.state.value.post?.body}")
                }
            }
        }

        return return binding.root
    }


}