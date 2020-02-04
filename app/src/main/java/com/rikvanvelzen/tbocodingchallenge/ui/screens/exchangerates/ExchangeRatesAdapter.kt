package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.rikvanvelzen.tbocodingchallenge.R
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinExchangeRate
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRateItemBinding
import java.util.*

class ExchangeRatesAdapter (private val viewModel: ExchangeRateViewModel,
                            private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var currencies: ArrayList<BitcoinExchangeRate> = ArrayList()

    /*********************************
     * Public functions
     *********************************/

    fun setData(data: List<BitcoinExchangeRate>) {

        currencies.clear()
        currencies.addAll(data)

        notifyDataSetChanged()
    }

    /*********************************
     * Override functions
     *********************************/

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewHolder = (holder as VHExchangeRateItem)
        viewHolder.bind(currencies[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = DataBindingUtil.inflate<ExchangeRateItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.exchange_rate_item, parent, false)

        return VHExchangeRateItem(binding, viewModel, lifecycleOwner)
    }

    override fun getItemCount() = currencies.size
}
