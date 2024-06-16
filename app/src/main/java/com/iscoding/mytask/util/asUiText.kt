package com.iscoding.mytask.util

import com.iscoding.mytask.R
import com.iscoding.mytask.domain.error.DataError
import com.iscoding.mytask.domain.error.Result

fun DataError.asUiText(): UiText {
    return when (this) {
        // Network Errors
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(R.string.the_request_timed_out)
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.youve_hit_your_rate_limit)
        DataError.Network.NO_INTERNET -> UiText.StringResource(R.string.no_internet)
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(R.string.file_too_large)
        DataError.Network.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
        DataError.Network.SERIALIZATION -> UiText.StringResource(R.string.error_serialization)
        DataError.Network.UNKNOWN -> UiText.StringResource(R.string.unknown_error)
        DataError.Network.SERVICE_UNAVAILABLE -> UiText.StringResource(R.string.service_unavailable)
        DataError.Network.NOT_IMPLEMENTED -> UiText.StringResource(R.string.not_implemented)
        DataError.Network.BAD_GATEWAY -> UiText.StringResource(R.string.bad_gateway)
        DataError.Network.GATEWAY_TIMEOUT -> UiText.StringResource(R.string.gateway_timeout)
        DataError.Network.METHOD_NOT_ALLOWED -> UiText.StringResource(R.string.method_not_allowed)
        DataError.Network.BAD_REQUEST -> UiText.StringResource(R.string.bad_request)
        DataError.Network.NOT_FOUND -> UiText.StringResource(R.string.not_found)
        DataError.Network.UNSUPPORTED_MEDIA_TYPE -> UiText.StringResource(R.string.unsupported_media_type)
        DataError.Network.CONFLICT -> UiText.StringResource(R.string.conflict)
        DataError.Network.PRECONDITION_FAILED -> UiText.StringResource(R.string.precondition_failed)

        // Database Errors
        DataError.DatabaseError.TIMEOUT -> UiText.StringResource(R.string.error_disk_full)
        DataError.DatabaseError.PERMISSION_DENIED -> TODO()
        DataError.DatabaseError.INSUFFICIENT_SPACE -> TODO()
        DataError.DatabaseError.DATABASE_LOCKED -> TODO()
        DataError.DatabaseError.DATA_NOT_FOUND -> TODO()
        DataError.DatabaseError.CONSTRAINT_VIOLATION -> TODO()
        DataError.DatabaseError.DATABASE_CORRUPTION -> TODO()
        DataError.DatabaseError.SCHEMA_MISMATCH -> TODO()
        DataError.DatabaseError.SERIALIZATION -> TODO()
        DataError.DatabaseError.UNKNOWN -> TODO()
    }
}

fun Result.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}