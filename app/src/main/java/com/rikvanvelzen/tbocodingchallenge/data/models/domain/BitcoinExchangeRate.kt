package com.rikvanvelzen.tbocodingchallenge.data.models.domain

/**
 * Bitcoin exchange [rate] value at specific [date] for [currency]
 */
data class BitcoinExchangeRate(

        val rate: Float,
        val date: String,
        val currency: String
)