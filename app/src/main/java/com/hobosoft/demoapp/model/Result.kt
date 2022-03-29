package com.hobosoft.demoapp.model

sealed class Result<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T): Result<T>(data)
    class Error<T>(errorMessage: String): Result<T>(null, errorMessage)
    class Loading<T>(): Result<T>()
}