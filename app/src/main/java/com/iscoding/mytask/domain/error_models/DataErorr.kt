package com.iscoding.mytask.domain.error_models


sealed interface DataError: Error {
    enum class Network: DataError {
        // Client Errors (4xx)

        /**
         * The request took too long to complete.
         */
        REQUEST_TIMEOUT, // 408

        /**
         * The user has sent too many requests in a given amount of time.
         */
        TOO_MANY_REQUESTS, // 429

        /**
         * The device is not connected to the internet.
         */
        NO_INTERNET, // No specific HTTP code, related to connectivity issues

        /**
         * The request entity is larger than the server is willing or able to process.
         */
        PAYLOAD_TOO_LARGE, // 413

        // Server Errors (5xx)

        /**
         * An error occurred on the server side.
         */
        SERVER_ERROR, // 500 Internal Server Error

        /**
         * The service is unavailable, usually due to overload or maintenance.
         */
        SERVICE_UNAVAILABLE, // 503

        /**
         * The server does not support the functionality required to fulfill the request.
         */
        NOT_IMPLEMENTED, // 501

        /**
         * The server is acting as a gateway or proxy and received an invalid response from the upstream server.
         */
        BAD_GATEWAY, // 502

        /**
         * The server is acting as a gateway and cannot reach the upstream server.
         */
        GATEWAY_TIMEOUT, // 504

        /**
         * The server does not recognize the request method, or it lacks the ability to fulfill the request.
         */
        METHOD_NOT_ALLOWED, // 405

        // Other Errors

        /**
         * The request could not be understood by the server due to malformed syntax.
         */
        BAD_REQUEST, // 400

        /**
         * The server cannot find the requested resource.
         */
        NOT_FOUND, // 404

        /**
         * The server cannot process the request because it is in an unsupported media format.
         */
        UNSUPPORTED_MEDIA_TYPE, // 415

        /**
         * A conflict occurred on the server, such as an edit conflict between multiple simultaneous updates.
         */
        CONFLICT, // 409

        /**
         * The request could not be completed due to a conflict with the current state of the target resource.
         */
        PRECONDITION_FAILED, // 412

        // Serialization and Other Local Errors

        /**
         * An error occurred during the serialization or deserialization process.
         */
        SERIALIZATION, // Serialization/Deserialization error

        /**
         * An unknown error occurred.
         */
        UNKNOWN // Any unspecified error
    }

    enum class DatabaseError: DataError {
        /**
         * The database operation timed out.
         * Database may return a specific error code or exception indicating a timeout.
         */
        TIMEOUT, // Operation took too long to complete

        /**
         * The database operation failed due to insufficient permissions.
         * Database may throw a permissions-related exception or return an error code.
         */
        PERMISSION_DENIED, // Lack of required permissions to perform the operation

        /**
         * An attempt to read or write to the database failed due to insufficient storage space.
         * Database may return an error code indicating lack of space or throw an IOException.
         */
        INSUFFICIENT_SPACE, // Not enough storage space available

        /**
         * The database is locked, preventing the operation from completing.
         * Database may throw a lock-related exception or return an error indicating a lock issue.
         */
        DATABASE_LOCKED, // The database is currently locked

        /**
         * The requested data was not found in the database.
         * Database query returns null or an empty result set.
         */
        DATA_NOT_FOUND, // The requested data does not exist

        /**
         * An attempt to write to the database failed due to a constraint violation.
         * Database returns an error code or throws an exception indicating a constraint violation.
         */
        CONSTRAINT_VIOLATION, // A database constraint was violated

        /**
         * An attempt to open the database failed due to corruption.
         * Database throws a corruption-related exception or returns an error code.
         */
        DATABASE_CORRUPTION, // The database is corrupted

        /**
         * The database schema does not match the expected schema.
         * Database operation fails with an error indicating schema mismatch or version conflict.
         */
        SCHEMA_MISMATCH, // The database schema is different from the expected schema

        /**
         * An error occurred during the serialization or deserialization process.
         * Database operation fails due to serialization issues, returning an error or exception.
         */
        SERIALIZATION, // Serialization/Deserialization error

        /**
         * An unspecified error occurred during the database operation.
         * Database returns a generic error code or throws a general exception.
         */
        UNKNOWN // Any unspecified error
    }
}