package com.rikvanvelzen.tbocodingchallenge.data.models.domain

/**
 * Bitcoin exchange [rate] value at time [lastUpdated] for [currency]
 */
data class BitcoinCurrentExchangeRate(

        val rate: String,
        val lastUpdated: String,
        val currency: String
)