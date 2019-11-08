package com.jeluchu.gradlebank.core.interactor

import com.jeluchu.gradlebank.core.exception.Failure
import com.jeluchu.gradlebank.core.funtional.Either
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        GlobalScope.launch(Dispatchers.Main) {
            val job = async(Dispatchers.IO) { run(params) }
            onResult(job.await())
        }
    }
    class None

}