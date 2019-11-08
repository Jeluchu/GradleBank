package com.jeluchu.gradlebank.features.accounts.usecase

import com.jeluchu.gradlebank.core.interactor.UseCase
import com.jeluchu.gradlebank.features.accounts.models.AccountX
import com.jeluchu.gradlebank.features.accounts.repository.AccountsRepository

class GetAccounts (private val accountsRepository: AccountsRepository): UseCase<List<AccountX>, UseCase.None>() {
    override suspend fun run(params: None) = accountsRepository.getAccounts()
}