package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import androidx.lifecycle.LifecycleOwner
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinExchangeRate
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRateItemBinding
import java.util.*

class VHExchangeRateItem(val binding: ExchangeRateItemBinding,
                         val viewModel: ExchangeRateViewModel,
                         val lifecycleOwner: LifecycleOwner) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    fun bind(exchangeRate: BitcoinExchangeRate) {


    }

}
