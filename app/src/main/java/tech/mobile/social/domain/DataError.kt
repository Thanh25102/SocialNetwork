package tech.mobile.social.domain

sealed interface DataError : Error {
    enum class NetworkError : DataError {
        NO_INTERNET_CONNECTION,
        TIMEOUT,
        UNKNOWN
    }

    class ServerErrors(val message: List<String>) : DataError

    enum class LocalError : DataError {
        DATABASE_ERROR,
        FILE_NOT_FOUND,
        UNKNOWN
    }
}