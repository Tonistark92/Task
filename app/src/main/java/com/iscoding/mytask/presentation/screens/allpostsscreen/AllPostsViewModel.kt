package com.iscoding.mytask.presentation.screens.allpostsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iscoding.mytask.domain.error.DataError
import com.iscoding.mytask.domain.error.Result
import com.iscoding.mytask.domain.usecases.PostsUseCase
import com.iscoding.mytask.util.Resource
import com.iscoding.mytask.util.asUiText
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
                when(result){
                    is Result.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                        when (result.error){

                            DataError.Network.REQUEST_TIMEOUT ->{
                                //this is a way for localization string resources errors using UI text
                                _state.value = _state.value.copy(errorMessage = result.error.asUiText())
                            }
                            DataError.Network.TOO_MANY_REQUESTS -> TODO()
                            DataError.Network.NO_INTERNET -> TODO()
                            DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
                            DataError.Network.SERVER_ERROR -> TODO()
                            DataError.Network.SERVICE_UNAVAILABLE -> TODO()
                            DataError.Network.NOT_IMPLEMENTED -> TODO()
                            DataError.Network.BAD_GATEWAY -> TODO()
                            DataError.Network.GATEWAY_TIMEOUT -> TODO()
                            DataError.Network.METHOD_NOT_ALLOWED -> TODO()
                            DataError.Network.BAD_REQUEST -> TODO()
                            DataError.Network.NOT_FOUND -> TODO()
                            DataError.Network.UNSUPPORTED_MEDIA_TYPE -> TODO()
                            DataError.Network.CONFLICT -> TODO()
                            DataError.Network.PRECONDITION_FAILED -> TODO()
                            DataError.Network.SERIALIZATION -> TODO()
                            DataError.Network.UNKNOWN -> TODO()
                        }
                    }
                    is Result.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                                posts = result.data,
                                isLoading = false
                            )
                    }
                }

            }


            // old approach


//            useCase.getAllPosts().collect { result ->
//                when (result) {
//                    is Resource.Loading -> {
//                        _state.value = _state.value.copy(isLoading = result.isLoading)
//                    }
//                    is Resource.Success -> {
//                            _state.value = _state.value.copy(
//                                posts = result.data!!,
//                                isLoading = false
//                            )
//
//                    }
//                    is Resource.Error -> {
//                        _state.value = _state.value.copy(isLoading = false)
//                    }
//                }
//            }
        }
    }
}