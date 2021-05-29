package id.capstone.hijoe.data.vo

sealed class Result<out R> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(
            val cause: RequestResult = RequestResult.NOT_DEFINED,
            val exception: Throwable
    ): Result<Nothing>()
}
