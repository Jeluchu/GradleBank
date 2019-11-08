package com.jeluchu.gradlebank.features.accounts.repository

import com.jeluchu.gradlebank.features.accounts.models.AccountEntity
import retrofit2.Call
import retrofit2.http.GET

interface AccountsApi {

    @GET("data.json")
    fun getAccounts(): Call<AccountEntity>

}