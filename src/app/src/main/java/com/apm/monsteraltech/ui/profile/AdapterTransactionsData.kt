package com.apm.monsteraltech.ui.profile

import android.content.Context
import android.provider.Settings
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apm.monsteraltech.R

class AdapterTransactionsData (private val transactionList: ArrayList<Transactions>): RecyclerView.Adapter<AdapterTransactionsData.ViewHolder>() {


    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        fun setData(transaction: Transactions) {
            val userSeller = transaction.seller
            val userCustomer = transaction.customer
            val item = transaction.item
            val date = transaction.date
            val transactionMessage = context.getString(R.string.transaction_template, userSeller, userCustomer, item, date)
            transactionMessageTextView.text = transactionMessage
        }

        val transactionMessageTextView: TextView = itemView.findViewById(R.id.transaction_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_transactions, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(transactionList[position])
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }
}
