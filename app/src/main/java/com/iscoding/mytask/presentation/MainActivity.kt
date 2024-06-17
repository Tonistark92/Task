package com.iscoding.mytask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iscoding.mytask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)


    }
}