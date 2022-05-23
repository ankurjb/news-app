package com.ankurjb.core.model

sealed class Either<out T> {
    data class Success<out T>(val response: T) : Either<T>()
    data class Loading(val progressMessage: String) : Either<Nothing>()
    data class Error(val errorMessage: String) : Either<Nothing>()
}
