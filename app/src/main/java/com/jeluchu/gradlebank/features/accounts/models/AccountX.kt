package com.jeluchu.gradlebank.features.accounts.models

import com.jeluchu.gradlebank.core.extensions.empty

data class AccountX(
    val accountBalanceInCents: Int?,
    val accountCurrency: String?,
    val accountId: String?,
    val accountName: String?,
    val accountNumber: String?,
    val accountType: String?,
    val alias: String?,
    val iban: String?,
    val isVisible: Boolean?,
    val linkedAccountId: Int?,
    val productName: String?,
    val productType: Int?,
    val savingsTargetReached: Int?,
    val targetAmountInCents: Int?
) {

    fun toAccountXView(): AccountXView =
        AccountXView(
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
        fun empty() = AccountX (
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