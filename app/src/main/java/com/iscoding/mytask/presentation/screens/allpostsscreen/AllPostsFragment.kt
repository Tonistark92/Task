package com.iscoding.mytask.presentation.screens.allpostsscreen

import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.iscoding.mytask.R
import com.iscoding.mytask.databinding.FragmentAllPostsBinding
import com.iscoding.mytask.presentation.screens.detailsScreen.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import de.syntax_institut.telefonbuch.adapter.ItemAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPostsFragment : Fragment() {
    private val viewModel: AllPostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllPosts()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAllPostsBinding.inflate(inflater, container, false)

  lifecycleScope.launchWhenStarted {
    viewModel.state.collect { state ->
//        if (state.isLoading) {
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
            binding.progressBar.visibility = View.GONE
            val itemAdapter = ItemAdapter()
            binding.recyclerView.adapter = itemAdapter
            itemAdapter.updateDataset(state.posts)
//        }
    }
}





        // Inflate the layout for this fragment
        return return binding.root
    }


}