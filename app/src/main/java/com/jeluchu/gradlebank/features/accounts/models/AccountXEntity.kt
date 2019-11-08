package com.jeluchu.gradlebank.features.accounts.models


import com.google.gson.annotations.SerializedName
import com.jeluchu.gradlebank.core.extensions.empty

data class AccountXEntity(
    @SerializedName("accountBalanceInCents")
    val accountBalanceInCents: Int?,
    @SerializedName("accountCurrency")
    val accountCurrency: String?,
    @SerializedName("accountId")
    val accountId: String?,
    @SerializedName("accountName")
    val accountName: String?,
    @SerializedName("accountNumber")
    val accountNumber: String?,
    @SerializedName("accountType")
    val accountType: String?,
    @SerializedName("alias")
    val alias: String?,
    @SerializedName("iban")
    val iban: String?,
    @SerializedName("isVisible")
    val isVisible: Boolean?,
    @SerializedName("linkedAccountId")
    val linkedAccountId: Int?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("productType")
    val productType: Int?,
    @SerializedName("savingsTargetReached")
    val savingsTargetReached: Int?,
    @SerializedName("targetAmountInCents")
    val targetAmountInCents: Int?
) {

    fun toAccountX(): AccountX =
        AccountX(
            accountBalanceInCents ?: Int.empty(),
            accountCurrency ?: String.empty(),
            accountId ?: String.empty(),
            accountName ?: String.empty(),
            accountNumber ?: String.empty(),
            accountType ?: String.empty(),
            alias ?: String.empty(),
            iban ?: String.empty(),
            isVisible ?: false,
            linkedAccountId ?: Int.empty(),
            productName ?: String.empty(),
            productType ?: Int.empty(),
            savingsTargetReached ?: Int.empty(),
            targetAmountInCents ?: Int.empty()
        )

    companion object {
        fun empty() = AccountXEntity(
            Int.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            false,
            Int.empty(),
            String.empty(),
            Int.empty(),
            Int.empty(),
            Int.empty()
        )
    }

}