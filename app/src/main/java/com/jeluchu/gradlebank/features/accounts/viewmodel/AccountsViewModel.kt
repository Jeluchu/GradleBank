package com.jeluchu.gradlebank.features.accounts.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jeluchu.gradlebank.core.platform.BaseViewModel
import com.jeluchu.gradlebank.core.interactor.UseCase
import com.jeluchu.gradlebank.features.accounts.models.AccountX
import com.jeluchu.gradlebank.features.accounts.models.AccountXView
import com.jeluchu.gradlebank.features.accounts.usecase.GetAccounts

class AccountsViewModel(private val getAccounts: GetAccounts): BaseViewModel() {

    var accounts: MutableLiveData<List<AccountXView>> = MutableLiveData()
    private var filterAccounts: List<AccountXView> = listOf()

    fun getAccounts() = getAccounts.invoke(UseCase.None()) { it.either(::handleFailure, ::handleAccounts) }
    private fun handleAccounts(accounts: List<AccountX>) {
        this.accounts.value = accounts.map { it.toAccountXView() }
        this.filterAccounts = accounts.map { it.toAccountXView() }
    }

    fun getAccountsFiltered() {
        val tempList : MutableList<AccountXView> = mutableListOf()
        for (account in filterAccounts){
            if(account.isVisible){
                tempList.add(account)
            }
        }
        this.accounts.value = tempList
    }
}
