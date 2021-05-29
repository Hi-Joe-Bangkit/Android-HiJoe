package id.capstone.hijoe.data.remote.source

import com.google.gson.JsonSyntaxException
import id.capstone.hijoe.data.vo.RequestResult
import id.capstone.hijoe.data.vo.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class StateRemoteDataSource {

    open suspend fun <T> safeApiCall(
            dispatcher: CoroutineDispatcher,
            apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        val errorResult = when(throwable.code()) {
                            in 500..599 -> parseError(
                                    RequestResult.SERVER_ERROR,
                                    throwable
                            )
                            else -> parseError(
                                    RequestResult.NOT_DEFINED,
                                    throwable
                            )
                        }
                        errorResult
                    }
                    is UnknownHostException -> parseError(
                            RequestResult.NO_CONNECTION,
                            throwable
                    )
                    is ConnectException -> parseError(
                            RequestResult.NO_CONNECTION,
                            throwable
                    )
                    is SocketTimeoutException -> parseError(
                            RequestResult.TIMEOUT,
                            throwable
                    )
                    is IOException -> parseError(
                            RequestResult.BAD_RESPONSE,
                            throwable
                    )
                    is JsonSyntaxException -> parseError(
                            RequestResult.DATA_NOT_MATCH,
                            throwable
                    )
                    else -> parseError(
                            RequestResult.NOT_DEFINED,
                            throwable
                    )
                }
            }
        }
    }

    private fun parseError(cause: RequestResult, e: Throwable) = Result.Error(cause, e)
}