package com.iscoding.mytask.presentation.screens.allpostsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iscoding.mytask.domain.error.DataError
import com.iscoding.mytask.domain.error.Result
import com.iscoding.mytask.domain.usecases.PostsUseCase
import com.iscoding.mytask.util.Resource
import com.iscoding.mytask.util.UiText
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
                            DataError.Network.BAD_REQUEST -> {
                                _state.value = _state.value.copy(errorMessage = UiText.DynamicStringList(result.messages!!))


                            }
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

/// this view modle if want to create retry api call
//@HiltViewModel
//class AllPostsViewModel @Inject constructor(
//    private val useCase: PostsUseCase
//) : ViewModel() {
//
//    private val _state = MutableStateFlow(AllPostsState())
//    val state get() = _state.asStateFlow()
//
//    private val numberOfRetries = 2
//    private val timeout = 5000L // 5 seconds
//
//    fun getAllPosts() {
//        viewModelScope.launch {
//            try {
//                val postsResult = retryWithTimeout(numberOfRetries, timeout) {
//                    useCase.getAllPosts().first()
//                }
//                handleResult(postsResult)
//            } catch (e: Exception) {
//                Timber.e(e, "Error fetching all posts")
//                _state.value = _state.value.copy(errorMessage = "Network Request failed")
//            }
//        }
//    }
//
//    private suspend fun handleResult(result: Result<List<Post>, DataError.Network>) {
//        when (result) {
//            is Result.Error -> {
//                _state.value = _state.value.copy(isLoading = false)
//                when (result.error) {
//                    DataError.Network.REQUEST_TIMEOUT -> {
//                        _state.value = _state.value.copy(errorMessage = result.error.asUiText())
//                        // Retry logic can be added here if desired
//                    }
//                    DataError.Network.TOO_MANY_REQUESTS -> TODO()
//                    DataError.Network.NO_INTERNET -> TODO()
//                    DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
//                    DataError.Network.SERVER_ERROR -> TODO()
//                    DataError.Network.SERVICE_UNAVAILABLE -> TODO()
//                    DataError.Network.NOT_IMPLEMENTED -> TODO()
//                    DataError.Network.BAD_GATEWAY -> TODO()
//                    DataError.Network.GATEWAY_TIMEOUT -> TODO()
//                    DataError.Network.METHOD_NOT_ALLOWED -> TODO()
//                    DataError.Network.BAD_REQUEST -> {
//                        _state.value = _state.value.copy(errorMessage = UiText.DynamicStringList(result.messages!!))
//                    }
//                    DataError.Network.NOT_FOUND -> TODO()
//                    DataError.Network.UNSUPPORTED_MEDIA_TYPE -> TODO()
//                    DataError.Network.CONFLICT -> TODO()
//                    DataError.Network.PRECONDITION_FAILED -> TODO()
//                    DataError.Network.SERIALIZATION -> TODO()
//                    DataError.Network.UNKNOWN -> TODO()
//                }
//            }
//            is Result.Loading -> {
//                _state.value = _state.value.copy(isLoading = result.isLoading)
//            }
//            is Result.Success -> {
//                _state.value = _state.value.copy(
//                    posts = result.data,
//                    isLoading = false
//                )
//            }
//        }
//    }
//
//    private suspend fun <T> retryWithTimeout(
//        numberOfRetries: Int,
//        timeout: Long,
//        block: suspend () -> T
//    ): Result<T, DataError.Network> {
//        var lastError: Throwable? = null
//        repeat(numberOfRetries) { attempt ->
//            try {
//                return withTimeout(timeout) {
//                    block().let {
//                        Result.Success(it)
//                    }
//                }
//            } catch (e: Exception) {
//                Timber.e(e, "Error on attempt $attempt")
//                lastError = e
//            }
//        }
//        return Result.Error(lastError?.let { mapExceptionToDataError(it) } ?: DataError.Network.UNKNOWN)
//    }
//
//    private fun mapExceptionToDataError(exception: Throwable): DataError.Network {
//        return when (exception) {
//            is IOException -> DataError.Network.NO_INTERNET
//            is TimeoutCancellationException -> DataError.Network.REQUEST_TIMEOUT
//            is HttpException -> {
//                when (exception.code()) {
//                    400 -> DataError.Network.BAD_REQUEST
//                    404 -> DataError.Network.NOT_FOUND
//                    408 -> DataError.Network.REQUEST_TIMEOUT
//                    429 -> DataError.Network.TOO_MANY_REQUESTS
//                    500 -> DataError.Network.SERVER_ERROR
//                    502 -> DataError.Network.BAD_GATEWAY
//                    503 -> DataError.Network.SERVICE_UNAVAILABLE
//                    504 -> DataError.Network.GATEWAY_TIMEOUT
//                    else -> DataError.Network.UNKNOWN
//                }
//            }
//            else -> DataError.Network.UNKNOWN
//        }
//    }
//}