package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.rikvanvelzen.tbocodingchallenge.R
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRateCurrentPriceItemBinding
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRateItemBinding
import com.rikvanvelzen.tbocodingchallenge.ui.screens.base.VHGenericRecyclerItem
import java.util.*

class ExchangeRatesAdapter(private val viewModel: ExchangeRateViewModel,
                           private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CURRENT_PRICE = 1
        private const val VIEW_TYPE_HEADER_HISTORICAL_DATA = 2
        private const val VIEW_TYPE_HISTORICAL_DATA_ITEM = 3
    }

    private var exchangeRates: ArrayList<BitcoinHistoricalExchangeRate> = ArrayList()

    /*********************************
     * Public functions
     *********************************/

    fun setData(data: List<BitcoinHistoricalExchangeRate>) {

        exchangeRates.clear()
        exchangeRates.addAll(data)

        notifyDataSetChanged()
    }

    /*********************************
     * Override functions
     *********************************/

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {

            VIEW_TYPE_HISTORICAL_DATA_ITEM -> (holder as VHHistoricalRateItem).bind(exchangeRates[position - 2])
            VIEW_TYPE_HEADER_HISTORICAL_DATA,
            VIEW_TYPE_CURRENT_PRICE -> {
                // Do nothing. All in viewHolder
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View

        return when (viewType) {
            VIEW_TYPE_HISTORICAL_DATA_ITEM -> {
                val binding = DataBindingUtil.inflate<ExchangeRateItemBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.exchange_rate_item, parent, false)

                VHHistoricalRateItem(binding)
            }
            VIEW_TYPE_HEADER_HISTORICAL_DATA -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.exchange_rate_historical_data_header, parent, false)
                VHGenericRecyclerItem(view)
            }
            // VIEW_TYPE_CURRENT_PRICE
            else -> {
                val binding = DataBindingUtil.inflate<ExchangeRateCurrentPriceItemBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.exchange_rate_current_price_item, parent, false)
                VHCurrentRateItem(binding, viewModel, lifecycleOwner)
            }
        }
    }

    override fun getItemCount() = exchangeRates.size + 2

    override fun getItemViewType(position: Int): Int = when (position) {

        0 -> VIEW_TYPE_CURRENT_PRICE
        1 -> VIEW_TYPE_HEADER_HISTORICAL_DATA
        else -> VIEW_TYPE_HISTORICAL_DATA_ITEM
    }

}
