package practice.effective.chooseyourhero.network

import practice.effective.chooseyourhero.network.dtos.ErrorDto

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ResponseWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorDto? = null) :
        ResponseWrapper<Nothing>()

    object NetworkError : ResponseWrapper<Nothing>()
}
