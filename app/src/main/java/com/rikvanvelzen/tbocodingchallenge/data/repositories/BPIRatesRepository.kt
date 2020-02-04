package com.rikvanvelzen.tbocodingchallenge.data.repositories

import com.rikvanvelzen.tbocodingchallenge.data.api.BitcoinPriceIndexApi
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinHistoricalValuesDTO
import io.reactivex.Single

interface BPIRatesRepository {

    fun getRates(startDate: String, endDate: String): Single<BitcoinHistoricalValuesDTO>
}

class BPIRatesRepositoryImpl(private val bitcoinPriceIndexService: BitcoinPriceIndexApi) : BPIRatesRepository{

    override fun getRates(startDate: String, endDate: String): Single<BitcoinHistoricalValuesDTO> {

        return bitcoinPriceIndexService.getHistoricalPrices(startDate, endDate)
    }
}