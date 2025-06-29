package com.iscoding.mytask.presentation.screens.allpostsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iscoding.mytask.domain.usecases.PostsUseCase
import com.iscoding.mytask.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllPostsViewModel @Inject constructor(
    private val useCase: PostsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AllPostsState())
    val state get() = _state.asStateFlow()

    fun getAllPosts() {
        viewModelScope.launch {
            useCase.getAllPosts().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                            _state.value = _state.value.copy(
                                posts = result.data!!,
                                isLoading = false
                            )

                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }
}