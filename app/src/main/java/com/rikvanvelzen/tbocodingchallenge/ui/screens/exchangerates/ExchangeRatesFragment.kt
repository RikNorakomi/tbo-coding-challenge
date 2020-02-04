package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.rikvanvelzen.tbocodingchallenge.R
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRatesFragmentBinding
import com.rikvanvelzen.tbocodingchallenge.ui.screens.base.MvvmBaseFragment


class ExchangeRatesFragment : MvvmBaseFragment<ExchangeRatesFragmentBinding, ExchangeRateViewModel>() {

    private lateinit var adapter: ExchangeRatesAdapter

    /**************************************************
     * Public functions
     **************************************************/

    override fun getLayoutResource(): Int = R.layout.exchange_rates_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExchangeRatesAdapter(viewModel, this)
        binding.fragmentExchangeRateRecycler.adapter = adapter

        setupObservers()
    }

    /**************************************************
     * Private functions
     **************************************************/

    private fun setupObservers() {

        viewModel.getExchangeRates().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

}