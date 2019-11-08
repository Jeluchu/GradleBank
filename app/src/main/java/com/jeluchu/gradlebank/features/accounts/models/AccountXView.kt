package com.jeluchu.gradlebank.features.accounts.models

data class AccountXView(
    val accountBalanceInCents: Int,
    val accountCurrency: String,
    val accountId: String,
    val accountName: String,
    val accountNumber: String,
    val accountType: String,
    val alias: String,
    val iban: String,
    val isVisible: Boolean,
    val linkedAccountId: Int,
    val productName: String,
    val productType: Int,
    val savingsTargetReached: Int,
    val targetAmountInCents: Int
)