package com.iscoding.mytask.presentation.screens.allpostsscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iscoding.mytask.databinding.FragmentAllPostsBinding
import dagger.hilt.android.AndroidEntryPoint
import com.iscoding.mytask.presentation.screens.allpostsscreen.adapter.ItemAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPostsFragment : Fragment() {
    private val viewModel: AllPostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAllPostsBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->

                if (state.errorMessage != null) {
                    Toast.makeText(
                        requireContext(),
                        state.errorMessage!!.asString(requireContext()), Toast.LENGTH_LONG
                    ).show()

                }
                if (state.isLoading) {
                    // Show progress bar when loading
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    // Hide progress bar when not loading
                    binding.progressBar.visibility = View.GONE

                    // Update RecyclerView adapter and dataset when not loading
                    val itemAdapter = ItemAdapter { postId ->
                        val action =
                            AllPostsFragmentDirections.actionAllPostsFragmentToDetailsFragment(
                                postId
                            )
                        findNavController().navigate(action)

                    }
                    binding.recyclerView.adapter = itemAdapter
                    itemAdapter.updateDataset(state.posts)
                }
            }
        }


        // Inflate the layout for this fragment
         return binding.root
    }


}