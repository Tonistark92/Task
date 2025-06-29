package com.iscoding.mytask.presentation.screens.detailsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.iscoding.mytask.domain.usecases.PostsUseCase
import com.iscoding.mytask.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: PostsUseCase,

) : ViewModel() {
    private val _state = MutableStateFlow(DetailsPostState())
    val state get() = _state.asStateFlow()
    var postId :Int =0

    fun setId(id: Int) {
        postId = id
    }

    fun getPost() {
        viewModelScope.launch {
            useCase.getPost(postId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        if (result.data?.id.toString().isEmpty()) {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                notFound = true
                            )
                        } else {
                            _state.value = _state.value.copy(
                                post = result.data,
                                isLoading = false,
                                notFound = false
                            )
                            Log.d("DATA" ,"viewmodel ${result.data.toString()}")

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