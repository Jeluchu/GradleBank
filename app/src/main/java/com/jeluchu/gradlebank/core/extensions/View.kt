package com.jeluchu.gradlebank.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeluchu.gradlebank.features.accounts.view.AccountAdapter

fun RecyclerView.setupLinearRecycler(context: FragmentActivity, adapt: AccountAdapter) {
    layoutManager = LinearLayoutManager(context)
    adapter = adapt
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

internal infix fun View.onClick(function: () -> Unit) {
    setOnClickListener { function() }
}
