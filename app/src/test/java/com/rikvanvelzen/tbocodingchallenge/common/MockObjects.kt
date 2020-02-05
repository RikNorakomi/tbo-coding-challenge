package com.rikvanvelzen.tbocodingchallenge.common

import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinHistoricalRatesDTO
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinPriceIndexDTO
//
//val EUR = "EUR"
//
//val lastUpdated1 = "lastUpdate1"
//val lastUpdated2 = "lastUpdate2"
//val rateFormatted1 = "rate1"
//val rateFormatted2 = "rate2"
//val currencyRateMap1 = mapOf(EUR to BitcoinPriceIndexDTO.Currency(null, null, null, rateFormatted1))
//val currencyRateMap2 = mapOf(EUR to BitcoinPriceIndexDTO.Currency(null, null, null, rateFormatted2))
//
//fun bitcoinPriceIndexDTO1() = BitcoinPriceIndexDTO(BitcoinPriceIndexDTO.Time(lastUpdated1), null, null, currencyRateMap1)
//fun bitcoinPriceIndexDTO2() = BitcoinPriceIndexDTO(BitcoinPriceIndexDTO.Time(lastUpdated2), null, null, currencyRateMap2)

private val date1 = "2020-02-01"
private val rate1 = 9388.6617F
private val date2 = "2020-02-02"
private val rate2 = 9311.6617F

fun getHistoricalRatesResultObject(): BitcoinHistoricalRatesDTO {

    val bpiMap = mapOf(date1 to rate1, date2 to rate2)
    val resultObject = BitcoinHistoricalRatesDTO(null, bpiMap, null)

    return resultObject
}