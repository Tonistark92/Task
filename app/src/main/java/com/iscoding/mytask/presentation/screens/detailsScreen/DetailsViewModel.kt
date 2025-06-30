package com.iscoding.mytask.presentation.screens.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iscoding.mytask.domain.error_models.DataError
import com.iscoding.mytask.domain.error_models.Result
import com.iscoding.mytask.domain.usecases.PostsUseCase
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

                when(result){
                    is Result.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                        when (result.error){

                            DataError.Network.REQUEST_TIMEOUT -> TODO()
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
                            post = result.data,
                            isLoading = false
                        )
                    }
                }





                // old approach
//                when (result) {
//                    is Resource.Loading -> {
//                        _state.value = _state.value.copy(isLoading = result.isLoading)
//                    }
//                    is Resource.Success -> {
//                        if (result.data?.id.toString().isEmpty()) {
//                            _state.value = _state.value.copy(
//                                isLoading = false,
//                                notFound = true
//                            )
//                        } else {
//                            _state.value = _state.value.copy(
//                                post = result.data,
//                                isLoading = false,
//                                notFound = false
//                            )
//                            Log.d("DATA" ,"viewmodel ${result.data.toString()}")
//
//                        }
//                    }
//
//                    is Resource.Error -> {
//                        _state.value = _state.value.copy(
//                            isLoading = false,
//                            notFound = true
//                        )
//                    }
//                }
//
            }
        }
    }

}