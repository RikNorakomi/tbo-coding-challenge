package com.rikvanvelzen.tbocodingchallenge.domain

import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinCurrentExchangeRate
import io.reactivex.Observable

interface CurrentBPIRateUseCase {

    fun getCurrentBitcoinRate(currency: String): Observable<BitcoinCurrentExchangeRate>
}