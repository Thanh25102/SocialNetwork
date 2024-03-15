package tech.mobile.social.domain.model


data class NetworkError(
    val error: ApiError,
    val throws: Throwable? = null
)
enum class ApiError(val message:String){
    NetworkError("Network Error"),
    UnknownResponse("Unknown Response"),
    UnknownError("Unknown Error")
}