package com.rikvanvelzen.tbocodingchallenge.domain

import android.annotation.SuppressLint
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import com.rikvanvelzen.tbocodingchallenge.data.repositories.BPIRatesRepository
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

const val DEFAULT_CURRENCY = "USD"

class HistoricalBPIRatesImpl(private val bpiRatesRepository: BPIRatesRepository) : HistoricalBPIRatesUseCase {

    @SuppressLint("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    /**************************************************
     * Public functions
     **************************************************/

    override fun getBitcoinRates(amountOfDaysInThePast: Int): Single<List<BitcoinHistoricalExchangeRate>> {

        return bpiRatesRepository.getHistoricalRates(getFromDate(amountOfDaysInThePast), getTillDate())
                .map { rates ->

                    val exchangeRates = mutableListOf<BitcoinHistoricalExchangeRate>()

                    rates.bpi?.forEach { (date, rate) ->
                        exchangeRates.add(BitcoinHistoricalExchangeRate(
                                rate,
                                date,
                                DEFAULT_CURRENCY))
                    }

                    exchangeRates
                }
    }

    /**************************************************
     * Private functions
     **************************************************/

    private fun getFromDate(amountOfDaysInThePastFromToday: Int): String {

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -amountOfDaysInThePastFromToday)

        val date: Date = calendar.time

        return dateFormat.format(date)
    }

    private fun getTillDate(): String = dateFormat.format(Calendar.getInstance().time)

}

