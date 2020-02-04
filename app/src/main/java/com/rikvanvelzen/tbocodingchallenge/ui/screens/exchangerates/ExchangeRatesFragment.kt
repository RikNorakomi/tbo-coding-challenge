package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rikvanvelzen.tbocodingchallenge.R
import com.rikvanvelzen.tbocodingchallenge.databinding.ExchangeRatesFragmentBinding
import com.rikvanvelzen.tbocodingchallenge.ui.screens.base.MvvmBaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


//class ExchangeRatesFragment : Fragment() {
class ExchangeRatesFragment : MvvmBaseFragment<ExchangeRatesFragmentBinding, ExchangeRateViewModel>() {

    private lateinit var adapter: ExchangeRatesAdapter

    @Inject
    lateinit var viewModel: ExchangeRateViewModel

    /**************************************************
     * Public functions
     **************************************************/

    override fun getLayoutResource(): Int = R.layout.exchange_rates_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hello()
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