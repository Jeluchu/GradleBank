package com.jeluchu.gradlebank.features.accounts.models

import com.google.gson.annotations.SerializedName

data class AccountEntity(
    @SerializedName("accounts")
    val accounts: List<AccountXEntity>
) {
    companion object { fun empty() = AccountEntity(emptyList()) }
}