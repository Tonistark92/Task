package com.iscoding.mytask.presentation.screens.allpostsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iscoding.mytask.R
import com.iscoding.mytask.data.remote.mapper.toTimedPost
import com.iscoding.mytask.domain.error_models.DataError
import com.iscoding.mytask.domain.error_models.Result
import com.iscoding.mytask.domain.usecases.PostsUseCase
import com.iscoding.mytask.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllPostsViewModel @Inject constructor(
    private val useCase: PostsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AllPostsState())
    val state get() = _state.asStateFlow()
    init {
        getAllPosts()
    }

    fun getAllPosts() {
        viewModelScope.launch {
            useCase.getAllPosts().collect { result ->
                when(result){

                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = mapErrorToUiText(result.error, result.messages)
                            )
                        }
                    }
                    is Result.Loading -> {
                        delay(2000)
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Result.Success -> {

                        _state.update { currentState ->
                            val list = result.data.map { it.toTimedPost() }
                            currentState.copy(
                                posts = list,
                                isLoading = false
                            )
                        }

                    }
                }

            }

        }


    }
    private fun mapErrorToUiText(error: DataError.Network, messages: List<String>? = null): UiText {
        return when (error) {
            DataError.Network.REQUEST_TIMEOUT ->
                UiText.StringResource(R.string.the_request_timed_out)

            DataError.Network.TOO_MANY_REQUESTS ->
                UiText.StringResource(R.string.youve_hit_your_rate_limit)

            DataError.Network.NO_INTERNET ->
                UiText.StringResource(R.string.no_internet)

            DataError.Network.PAYLOAD_TOO_LARGE ->
                UiText.StringResource(R.string.file_too_large)

            DataError.Network.SERVER_ERROR ->
                UiText.StringResource(R.string.server_error)

            DataError.Network.SERVICE_UNAVAILABLE ->
                UiText.StringResource(R.string.service_unavailable)

            DataError.Network.NOT_IMPLEMENTED ->
                UiText.StringResource(R.string.not_implemented)

            DataError.Network.BAD_GATEWAY ->
                UiText.StringResource(R.string.bad_gateway)

            DataError.Network.GATEWAY_TIMEOUT ->
                UiText.StringResource(R.string.gateway_timeout)

            DataError.Network.METHOD_NOT_ALLOWED ->
                UiText.StringResource(R.string.method_not_allowed)

            DataError.Network.BAD_REQUEST -> {
                val safeMessages = messages.orEmpty()
                if (safeMessages.isNotEmpty()) {
                    UiText.DynamicStringList(safeMessages)
                } else {
                    UiText.StringResource(R.string.bad_request)
                }
            }

            DataError.Network.NOT_FOUND ->
                UiText.StringResource(R.string.not_found)

            DataError.Network.UNSUPPORTED_MEDIA_TYPE ->
                UiText.StringResource(R.string.unsupported_media_type)

            DataError.Network.CONFLICT ->
                UiText.StringResource(R.string.conflict)

            DataError.Network.PRECONDITION_FAILED ->
                UiText.StringResource(R.string.precondition_failed)

            DataError.Network.SERIALIZATION ->
                UiText.StringResource(R.string.error_serialization)

            DataError.Network.UNKNOWN ->
                UiText.StringResource(R.string.unknown_error)
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


/// this viewmodel if want to create retry api call
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