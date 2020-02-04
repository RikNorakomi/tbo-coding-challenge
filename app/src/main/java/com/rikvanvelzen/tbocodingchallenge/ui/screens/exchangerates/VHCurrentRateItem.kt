package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import androidx.lifecycle.LifecycleOwner
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRateCurrentPriceItemBinding
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRateItemBinding

class VHCurrentRateItem(binding: ExchangeRateCurrentPriceItemBinding,
                        viewModel: ExchangeRateViewModel,
                        lifecycleOwner: LifecycleOwner) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

   init {
       // necessary bindings for updating latest bitcoin price
       binding.viewModel = viewModel
       binding.lifecycleOwner = lifecycleOwner
   }
}
