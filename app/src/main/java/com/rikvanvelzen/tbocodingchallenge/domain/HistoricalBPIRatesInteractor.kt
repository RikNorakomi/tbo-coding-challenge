package com.rikvanvelzen.tbocodingchallenge.domain

import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinExchangeRate
import com.rikvanvelzen.tbocodingchallenge.data.repositories.BPIRatesRepository
import io.reactivex.Single

class HistoricalBPIRatesInteractor(private val bpiRatesRepository: BPIRatesRepository) : HistoricalBPIRatesUseCase {

    override fun getBitcoinRates(): Single<List<BitcoinExchangeRate>> {

        val startDate = ""
        val endDate = ""

        return bpiRatesRepository.getRates(startDate, endDate)
//                .map { rates -> rates.map(cryptoViewModelMapper) }
                .map { rates ->

                    val exchangeRates = mutableListOf<BitcoinExchangeRate>()

                    rates.bpi.forEach { (currency, rate) ->
                        exchangeRates.add(BitcoinExchangeRate(
                                rate,
                                rates.time.updatedUTC ?: "",
                                currency))
                    }

                    exchangeRates
                }

    }

//    private val cryptoViewModelMapper: (BitcoinHistoricalValuesDTO) -> CryptoViewModel = {
//        crypto -> CryptoViewModel(crypto.id, crypto.name, crypto.symbol, crypto.rank, crypto.priceUsd.toFloat(), crypto.priceBtc.toFloat(), crypto.percentChange24h.toFloat())
//    }
}