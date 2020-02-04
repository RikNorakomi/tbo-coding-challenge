package com.rikvanvelzen.tbocodingchallenge.data.repositories

import com.rikvanvelzen.tbocodingchallenge.data.api.BitcoinPriceIndexApi
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinHistoricalRatesDTO
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinPriceIndexDTO
import io.reactivex.Observable
import io.reactivex.Single

interface BPIRatesRepository {

    fun getHistoricalRates(startDate: String, endDate: String): Single<BitcoinHistoricalRatesDTO>

    fun getCurrentRate(): Observable<BitcoinPriceIndexDTO>
}

class BPIRatesRepositoryImpl(private val bitcoinPriceIndexService: BitcoinPriceIndexApi) : BPIRatesRepository{

    override fun getHistoricalRates(startDate: String, endDate: String): Single<BitcoinHistoricalRatesDTO> {

        return bitcoinPriceIndexService.getHistoricalPrices(startDate, endDate)
    }

    override fun getCurrentRate(): Observable<BitcoinPriceIndexDTO> {

        return bitcoinPriceIndexService.getCurrentPrice()
    }
}