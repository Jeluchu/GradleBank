package com.jeluchu.gradlebank.features.accounts.view

import android.os.Bundle
import android.view.View
import com.jeluchu.gradlebank.R
import com.jeluchu.gradlebank.core.exception.Failure
import com.jeluchu.gradlebank.core.extensions.*
import com.jeluchu.gradlebank.core.extensions.onClick
import com.jeluchu.gradlebank.core.funtional.DialogCallback
import com.jeluchu.gradlebank.core.platform.BaseFragment
import com.jeluchu.gradlebank.core.utils.Constants.Companion.HIDEDATA
import com.jeluchu.gradlebank.core.utils.Constants.Companion.SHOWDATA
import com.jeluchu.gradlebank.features.accounts.models.AccountXView
import com.jeluchu.gradlebank.features.accounts.viewmodel.AccountsViewModel
import kotlinx.android.synthetic.main.fragment_accounts.*
import org.koin.android.ext.android.inject

class AccountsFragment : BaseFragment() {
    override fun layoutId(): Int = R.layout.fragment_accounts

    private val accountsAdapter: AccountAdapter by inject()
    private val getAccountsViewModel: AccountsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(getAccountsViewModel){
            observe(accounts, ::renderAccountList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initializeListeners()
        loadAccounts()
    }

    private fun initializeViews() {
        activity?.let { rvAccount.setupLinearRecycler(it, accountsAdapter) }
    }

    private fun initializeListeners() {
        scShowHide.onClick {
            when(scShowHide.isChecked) {
                true -> {
                    getAccountsViewModel.getAccounts()
                    hiddenLabel.text = HIDEDATA
                }
                false -> {
                    getAccountsViewModel.getAccountsFiltered()
                    hiddenLabel.text = SHOWDATA
                }
            }
        }
    }

    private fun loadAccounts() {
        showProgress()
        when(scShowHide.isChecked) {
            true -> getAccountsViewModel.getAccounts()
            false -> getAccountsViewModel.getAccountsFiltered()
        }
    }

    private fun renderAccountList(accounts: List<AccountXView>?) {
        accountsAdapter.collection = accounts.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when(failure) {
            is Failure.CustomError -> renderFailure(failure.errorCode, failure.errorMessage)
            else -> renderFailure(0, "")
        }
    }

    private fun renderFailure(errorCode: Int, errorMessage: String?) {
        hideProgress()
        showError(errorCode, errorMessage, object : DialogCallback {
            override fun onAccept() { loadAccounts() }
            override fun onDecline() { onBackPressed() }
        })
    }

}
