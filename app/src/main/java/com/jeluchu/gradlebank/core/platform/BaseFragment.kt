package com.jeluchu.gradlebank.core.platform

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeluchu.gradlebank.R
import com.jeluchu.gradlebank.core.funtional.DialogCallback
import com.jeluchu.gradlebank.core.navigation.PopUpDelegator
import kotlinx.android.synthetic.main.fragment_accounts.*

abstract class BaseFragment: androidx.fragment.app.Fragment() {

    private var popUpDelegator: PopUpDelegator? = null

    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) {
             if (this is BaseActivity) this.progress.visibility = viewStatus
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PopUpDelegator) {
            this.popUpDelegator = context
        }
    }

    open fun onBackPressed() {}

    private fun showErrorWithRetry(title: String, message: String, positiveText: String,
                                   negativeText: String, callback: DialogCallback
    ) {
        popUpDelegator?.showErrorWithRetry(title, message, positiveText, negativeText, callback)
    }

    internal fun showError(errorCode: Int, errorMessage: String?, dialogCallback: DialogCallback) {
        val genericErrorTitle = getString(R.string.generic_error_title)
        val genericErrorMessage = getString(R.string.generic_error_body)
        showErrorWithRetry(
            genericErrorTitle,
            genericErrorMessage,
            getString(R.string.Retry),
            getString(R.string.Cancel),
            object : DialogCallback {
                override fun onDecline() = dialogCallback.onDecline()
                override fun onAccept() = dialogCallback.onAccept()
            })
    }

}