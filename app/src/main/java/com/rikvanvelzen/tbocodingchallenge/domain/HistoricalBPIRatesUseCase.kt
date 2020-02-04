package com.rikvanvelzen.tbocodingchallenge.domain

import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinExchangeRate
import io.reactivex.Single

interface HistoricalBPIRatesUseCase {

    fun getBitcoinRates() : Single<List<BitcoinExchangeRate>>
}

