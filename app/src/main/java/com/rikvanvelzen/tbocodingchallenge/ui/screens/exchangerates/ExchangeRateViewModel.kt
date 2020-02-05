package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.modules.SCHEDULER_IO
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.modules.SCHEDULER_MAIN_THREAD
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinCurrentExchangeRate
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.Currencies
import com.rikvanvelzen.tbocodingchallenge.domain.CurrentBPIRateUseCase
import com.rikvanvelzen.tbocodingchallenge.domain.HistoricalBPIRatesUseCase
import com.rikvanvelzen.tbocodingchallenge.ui.screens.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named


class ExchangeRateViewModel
@Inject constructor(
        private val historicalBPIRatesUseCase: HistoricalBPIRatesUseCase,
        private val currentBPIRateUseCase: CurrentBPIRateUseCase,
        @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
        @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler) : BaseViewModel() {

    private val defaultCurrency = Currencies.USD

    var error = MutableLiveData<Throwable>()
    var currentRate = MutableLiveData<BitcoinCurrentExchangeRate>()
    var historicalExchangeRates = MutableLiveData<List<BitcoinHistoricalExchangeRate>>()

    /**************************************************
     * Public functions
     **************************************************/

    fun getHistoricalRates(): MutableLiveData<List<BitcoinHistoricalExchangeRate>> {

        loadHistoricalBPIRates()

        return historicalExchangeRates
    }

    fun getCurrentRate(){
        loadCurrentBPIRate()
    }

    fun onSwipeToRefresh() {
        disposables.clear()
        loadHistoricalBPIRates()
        loadCurrentBPIRate()
    }

    /**************************************************
     * Private functions
     **************************************************/

    @SuppressLint("CheckResult")
    private fun loadHistoricalBPIRates() {

        historicalBPIRatesUseCase.getBitcoinRates(14)
                .doOnSubscribe { isLoading.postValue(true) }
                .doAfterTerminate { isLoading.postValue(false) }
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(this::onHistoricalRatesReceived, this::onError)
                .also { disposables.add(it) }
    }

    private fun loadCurrentBPIRate() {

        Observable.interval(0, 1, TimeUnit.MINUTES)
                .flatMap { currentBPIRateUseCase.getCurrentBitcoinRate(defaultCurrency.abbreviation) }
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(this::onCurrentRateReceived, this::onError)
                .also { disposables.add(it) }
    }

    private fun onCurrentRateReceived(currentRate: BitcoinCurrentExchangeRate) = this.currentRate.postValue(currentRate)

    private fun onHistoricalRatesReceived(exchangeRates: List<BitcoinHistoricalExchangeRate>) {
        this.historicalExchangeRates?.value = exchangeRates.reversed()
    }

    private fun onError(t: Throwable) {
        error.postValue(t)
    }

}