package com.rikvanvelzen.tbocodingchallenge.domain

import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinCurrentExchangeRate
import com.rikvanvelzen.tbocodingchallenge.data.repositories.BPIRatesRepository
import io.reactivex.Observable

class CurrentBPIRateUseCaseImpl(private val bpiRatesRepository: BPIRatesRepository) : CurrentBPIRateUseCase {

    override fun getCurrentBitcoinRate(currency: String): Observable<BitcoinCurrentExchangeRate> {

        return bpiRatesRepository.getCurrentRate()

                .map { DTO ->

                    val rate = DTO.priceIndexMap?.get(currency)?.rateFormatted ?: ""
                    val lastUpdate = DTO.time?.updatedUTC ?: ""

                    BitcoinCurrentExchangeRate(rate, lastUpdate, currency)
                }
    }
}
