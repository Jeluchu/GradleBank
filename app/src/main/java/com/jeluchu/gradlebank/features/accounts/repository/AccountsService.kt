package com.jeluchu.gradlebank.features.accounts.repository

import com.jeluchu.gradlebank.features.accounts.models.AccountEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class AccountsService(retrofit: Retrofit): AccountsApi {
    private val serviceApi by lazy { retrofit.create(AccountsApi::class.java)  }
    override fun getAccounts(): Call<AccountEntity> = serviceApi.getAccounts()
}