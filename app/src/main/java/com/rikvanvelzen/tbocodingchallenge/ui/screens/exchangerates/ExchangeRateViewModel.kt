package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.application.SCHEDULER_IO
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.application.SCHEDULER_MAIN_THREAD
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinExchangeRate
import com.rikvanvelzen.tbocodingchallenge.domain.FetchCurrentExchangeRateUseCase
import com.rikvanvelzen.tbocodingchallenge.domain.HistoricalBPIRatesUseCase
import com.rikvanvelzen.tbocodingchallenge.ui.screens.base.BaseViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ExchangeRateViewModel
@Inject constructor(
        private val historicalBPIRatesUseCase: HistoricalBPIRatesUseCase,
        @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
        @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler) : BaseViewModel() {

    private var exchangeRates: MutableLiveData<List<BitcoinExchangeRate>>? = null

    /**************************************************
     * Public functions
     **************************************************/

    fun getExchangeRates(): MutableLiveData<List<BitcoinExchangeRate>> {

        if (exchangeRates == null) {
            exchangeRates = MutableLiveData()
            loadHistoricalBPIRates()
        }

        return exchangeRates as MutableLiveData<List<BitcoinExchangeRate>>
    }

    /**************************************************
     * Private functions
     **************************************************/

    @SuppressLint("CheckResult")
    private fun loadHistoricalBPIRates() {

        historicalBPIRatesUseCase.getBitcoinRates()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(this::onCryptoListReceived, this::onError)
    }

    private fun onCryptoListReceived(exchangeRates: List<BitcoinExchangeRate>) {


    }

    private fun onError(t: Throwable) {

    }

    fun hello() {

        Log.e(TAG, "Hello!")
    }
}