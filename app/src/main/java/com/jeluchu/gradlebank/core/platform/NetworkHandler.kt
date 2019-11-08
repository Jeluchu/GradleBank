package com.jeluchu.gradlebank.core.platform

import android.content.Context
import com.jeluchu.gradlebank.core.extensions.checkNetworkState

class NetworkHandler
(private val context: Context) {
    val isConnected get() = context.checkNetworkState()
}