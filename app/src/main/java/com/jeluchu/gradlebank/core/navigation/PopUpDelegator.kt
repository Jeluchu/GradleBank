package com.jeluchu.gradlebank.core.navigation

import com.jeluchu.gradlebank.core.funtional.DialogCallback

interface PopUpDelegator {

    fun showErrorWithRetry(title: String, message: String, positiveText: String,
                           negativeText: String, callback: DialogCallback
    )
}