package com.rikvanvelzen.tbocodingchallenge.domain

import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import io.reactivex.Single


interface HistoricalBPIRatesUseCase {

    /**
     * [amountOfDaysInThePast] determines from how many days in the past the bitcoin values are retrieved
     */
    fun getBitcoinRates(amountOfDaysInThePast: Int): Single<List<BitcoinHistoricalExchangeRate>>
}

