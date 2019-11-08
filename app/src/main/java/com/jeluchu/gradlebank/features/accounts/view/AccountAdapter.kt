package com.jeluchu.gradlebank.features.accounts.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeluchu.gradlebank.R
import com.jeluchu.gradlebank.core.extensions.inflate
import com.jeluchu.gradlebank.features.accounts.models.AccountXView
import kotlinx.android.synthetic.main.item_account.view.*
import kotlin.properties.Delegates

class AccountAdapter : RecyclerView.Adapter<AccountAdapter.ViewHolder>(){

    private var clickListener: (AccountXView) -> Unit = { }
    internal var collection: List<AccountXView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_account))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(collection[position], clickListener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(account: AccountXView, clickListener: (AccountXView) -> Unit) {
            itemView.tvAccountName.text = account.accountName
            itemView.tvBalance.text = account.accountBalanceInCents.toString()
            itemView.tvIban.text = account.iban
            itemView.setOnClickListener { clickListener(account) }
        }
    }
}

