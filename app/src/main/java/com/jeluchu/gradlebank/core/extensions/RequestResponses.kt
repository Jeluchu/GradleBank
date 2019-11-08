package com.jeluchu.gradlebank.core.extensions

import com.jeluchu.gradlebank.core.exception.Failure
import com.jeluchu.gradlebank.core.funtional.Either
import retrofit2.Call

fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform((response.body() ?: default)))
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.ServerError)
    }
}