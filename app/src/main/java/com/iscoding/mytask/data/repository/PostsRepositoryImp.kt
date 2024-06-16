package com.iscoding.mytask.data.repository

import android.util.Log
import com.iscoding.mytask.data.remote.PostRemoteDataSource
import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.domain.error.DataError
import com.iscoding.mytask.domain.error.Error
import com.iscoding.mytask.domain.error.Result
import com.iscoding.mytask.domain.repository.PostsRepository
import com.iscoding.mytask.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val api: PostRemoteDataSource,
) : PostsRepository {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Flow<com.iscoding.mytask.domain.error.Result<T, DataError.Network>> =
        flow {
            try {
                emit(Result.Loading(isLoading = true))
                val result = apiCall()
                emit(Result.Success(data = result))
                emit(Result.Loading(isLoading = false))
            } catch (e: HttpException) {
                when (e.code()) {
                    // Client Errors (4xx)
                    400 ->{
                        val errorResponse = e.response()?.errorBody()?.string()
                        // this for one error
//                        val messages = extractMessageFromErrorResponse(errorResponse)
                        // this for list of errors
                        val messages = extractMessagesFromErrorResponse(errorResponse)
                        if (messages.isNotEmpty()) {
                            emit(Result.Error(DataError.Network.BAD_REQUEST, messages))
                        } else {
                            emit(Result.Error(DataError.Network.BAD_REQUEST, listOf("Unknown error occurred")))
                        }
                    } // Bad Request
                    404 -> emit(Result.Error(DataError.Network.NOT_FOUND)) // Not Found
                    405 -> emit(Result.Error(DataError.Network.METHOD_NOT_ALLOWED)) // Method Not Allowed
                    408 -> emit(Result.Error(DataError.Network.REQUEST_TIMEOUT)) // Request Timeout
                    409 -> emit(Result.Error(DataError.Network.CONFLICT)) // Conflict
                    412 -> emit(Result.Error(DataError.Network.PRECONDITION_FAILED)) // Precondition Failed
                    413 -> emit(Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)) // Payload Too Large
                    415 -> emit(Result.Error(DataError.Network.UNSUPPORTED_MEDIA_TYPE)) // Unsupported Media Type
                    429 -> emit(Result.Error(DataError.Network.TOO_MANY_REQUESTS)) // Too Many Requests

                    // Server Errors (5xx)
                    500 -> emit(Result.Error(DataError.Network.SERVER_ERROR)) // Internal Server Error
                    501 -> emit(Result.Error(DataError.Network.NOT_IMPLEMENTED)) // Not Implemented
                    502 -> emit(Result.Error(DataError.Network.BAD_GATEWAY)) // Bad Gateway
                    503 -> emit(Result.Error(DataError.Network.SERVICE_UNAVAILABLE)) // Service Unavailable
                    504 -> emit(Result.Error(DataError.Network.GATEWAY_TIMEOUT)) // Gateway Timeout

                    // Other Errors
                    else -> emit(Result.Error(DataError.Network.UNKNOWN)) // Any unspecified error
                }
            } catch (e: IOException) {
                emit(Result.Error(DataError.Network.NO_INTERNET)) // No internet connection
            } catch (e: Exception) {
                emit(Result.Error(DataError.Network.UNKNOWN)) // Any other unknown error
            }
        }
    private fun extractMessageFromErrorResponse(errorResponse: String?): String {
        errorResponse?.let {
            try {
                val jsonObject = JSONObject(it)
                return jsonObject.getString("message")
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return "Unknown error occurred"
    }
    private fun extractMessagesFromErrorResponse(errorResponse: String?): List<String> {
        val messages = mutableListOf<String>()
        errorResponse?.let {
            try {
                val jsonObject = JSONObject(it)
                if (jsonObject.has("problem")) {
                    // Single error message structure
                    messages.add(jsonObject.getString("problem"))
                } else if (jsonObject.has("errors")) {
                    // Multiple errors structure
                    val errorsObject = jsonObject.getJSONObject("errors")
                    val keys = errorsObject.keys()
                    while (keys.hasNext()) {
                        val key = keys.next()
                        val errorMessage = errorsObject.getString(key)
                        messages.add(errorMessage)
                    }
                } else {
                    // Handle any other unexpected structure
                    messages.add("Unknown error occurred")
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                messages.add("Error parsing response")
            }
        } ?: run {
            messages.add("Empty or null response")
        }
        return messages
    }

    override suspend fun getAllPosts(): Flow<com.iscoding.mytask.domain.error.Result<List<Post>, DataError.Network>> =
        safeApiCall { api.getAllPosts() }

    override suspend fun getPost(id: Int): Flow<com.iscoding.mytask.domain.error.Result<Post, DataError.Network>> =
        safeApiCall { api.getPost(postId = id) }
}