package com.jeluchu.gradlebank.features.accounts.repository

import com.jeluchu.gradlebank.core.exception.Failure
import com.jeluchu.gradlebank.core.extensions.request
import com.jeluchu.gradlebank.core.funtional.Either
import com.jeluchu.gradlebank.core.platform.NetworkHandler
import com.jeluchu.gradlebank.features.accounts.models.AccountEntity
import com.jeluchu.gradlebank.features.accounts.models.AccountX

interface AccountsRepository {
    
    fun getAccounts(): Either<Failure, List<AccountX>>
    
    class Network(private val networkHandler: NetworkHandler,
                  private val service: AccountsService) : AccountsRepository {
        override fun getAccounts(): Either<Failure, List<AccountX>> = when(networkHandler.isConnected) {
            true -> request(
                service.getAccounts(),
                { accountEntity ->
                    accountEntity.accounts.map { it.toAccountX() }
                },
                AccountEntity.empty()
            )
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}