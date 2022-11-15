package practice.effective.chooseyourhero.network

import com.squareup.moshi.Moshi
import java.io.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import practice.effective.chooseyourhero.network.dtos.ErrorDto
import practice.effective.chooseyourhero.network.dtos.ErrorDtoJsonAdapter
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResponseWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResponseWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResponseWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResponseWrapper.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorDto? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            ErrorDtoJsonAdapter(Moshi.Builder().build()).fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}
