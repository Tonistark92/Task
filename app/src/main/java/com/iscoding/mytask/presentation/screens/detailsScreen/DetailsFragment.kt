package com.iscoding.mytask.presentation.screens.detailsScreen

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iscoding.mytask.R
import com.iscoding.mytask.databinding.FragmentAllPostsBinding
import com.iscoding.mytask.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
//        val detailsViewModel: DetailsViewModel by viewModels()
//        binding.tvItemTitle.text = detailsViewModel.state.value.post?.title
//        binding.tvItemBody.text = detailsViewModel.state.value.post?.body
        return return binding.root
    }


}