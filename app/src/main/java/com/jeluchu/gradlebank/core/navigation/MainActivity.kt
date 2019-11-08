package com.jeluchu.gradlebank.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.jeluchu.gradlebank.core.funtional.DialogCallback
import com.jeluchu.gradlebank.R
import com.jeluchu.gradlebank.core.utils.Constants.Companion.ACCOUNTS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PopUpDelegator {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val navController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = when (destination.id) {
                R.id.accountsFragment -> ACCOUNTS
                else -> ACCOUNTS
            }
        }

    }

    override fun showErrorWithRetry(
        title: String,
        message: String,
        positiveText: String,
        negativeText: String,
        callback: DialogCallback) {}
}