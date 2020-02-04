package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRateItemBinding

class VHHistoricalRateItem(private val binding: ExchangeRateItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    fun bind(exchangeRate: BitcoinHistoricalExchangeRate) {

        binding.exchangeRate = exchangeRate
    }
}
