package com.rikvanvelzen.tbocodingchallenge.data.models.dto

import com.google.gson.annotations.SerializedName


// todo check if fields should by nullable
data class BitcoinPriceIndexDTO(

        val time: Time,
        val disclaimer: String,
        val chartName: String,
        @SerializedName("bpi") val priceIndexMap: Map<String, Currency>
) {
    data class Time(
            @SerializedName("updated") val updatedUTC: String? = null,
            @SerializedName("updatedISO") val updatedISO: String? = null,
            @SerializedName("updateduk") val updatedUK: String? = null
    )

    /**
     * By default BPIs are published for [code] USD, EUR, and GBP
     */
    data class Currency(
            val code: String,
            val description: String,
            val symbol: String,
            @SerializedName("rate") val rateFormatted: String,
            @SerializedName("rate_float") val rate: Float
    )
}
