package com.app.bankmisr.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.bankmisr.data.CurrencyHistory
import com.app.bankmisr.databinding.ItemCurrencyHistoryBinding

class CurrencyHistoryAdapter :
    ListAdapter<CurrencyHistory, CurrencyHistoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCurrencyHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
    }

    class ViewHolder(private val view: ItemCurrencyHistoryBinding) : RecyclerView.ViewHolder(view.root){
        fun bindItem(item : CurrencyHistory){
            view.tvDate.text = item.date
            view.tvCurrency.text = item.currency
            view.tvRateValue.text = item.rate.toString()
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<CurrencyHistory>() {

        override fun areItemsTheSame(oldItem: CurrencyHistory, newItem: CurrencyHistory) =
            oldItem.date == newItem.date

        override fun areContentsTheSame(oldItem: CurrencyHistory, newItem: CurrencyHistory) =
            oldItem == newItem
    }


}