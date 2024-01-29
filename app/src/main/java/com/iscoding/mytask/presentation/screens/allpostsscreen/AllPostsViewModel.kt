package com.iscoding.mytask.presentation.screens.allpostsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iscoding.mytask.domain.usecases.PostsUseCase
import com.iscoding.mytask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllPostsViewModel @Inject constructor(
    private val useCase: PostsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AllPostsState())
    val state: MutableStateFlow<AllPostsState> get() = _state

    fun getAllPosts() {
        viewModelScope.launch {
            useCase.getAllPosts().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        if (result.data.isNullOrEmpty()) {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                notFound = true
                            )
                        } else {
                            _state.value = _state.value.copy(
                                posts = result.data,
                                isLoading = false,
                                notFound = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            notFound = true
                        )
                    }
                }

            }
        }
    }

}