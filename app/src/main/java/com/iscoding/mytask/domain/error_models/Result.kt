package com.iscoding.mytask.domain.error_models

typealias RootError = Error

sealed interface Result<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): Result<D, E>
    // this for one error
//    data class Error<out D, out E: RootError>(val error: E, val message: String? = null): Result<D, E>
    // this for list of errors
    data class Error<out D, out E: RootError>(val error: E, val messages: List<String>? = null): Result<D, E>
    data class Loading(val isLoading: Boolean): Result<Nothing, Nothing>
}
//examples
//{
//    "result": "success",
//    "code": 400,
//    "timestamp": "2024-06-16T17:44:22.662836Z",
//    "problem": "password is too small."
//}
//and this is the response when there are many error
//{
//    "result": "success",
//    "code": 400,
//    "timestamp": "2024-06-16T17:44:22.662836Z",
//    "errors": {"password is toosmall", "password should have numbers"}
//}