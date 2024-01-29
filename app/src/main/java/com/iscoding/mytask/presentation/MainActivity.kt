package com.iscoding.mytask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.iscoding.mytask.R
import com.iscoding.mytask.databinding.ActivityMainBinding
import com.iscoding.mytask.presentation.screens.allpostsscreen.AllPostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)


    }
}