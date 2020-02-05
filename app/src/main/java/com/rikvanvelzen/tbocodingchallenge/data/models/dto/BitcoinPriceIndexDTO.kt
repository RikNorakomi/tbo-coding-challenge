package com.rikvanvelzen.tbocodingchallenge.data.models.dto

import com.google.gson.annotations.SerializedName


data class BitcoinPriceIndexDTO(

        val time: Time? = null,
        val disclaimer: String? = null,
        val chartName: String? = null,
        @SerializedName("bpi") val priceIndexMap: Map<String, Currency>? = null
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
            val code: String? = null,
            val description: String? = null,
            val symbol: String? = null,
            @SerializedName("rate") val rateFormatted: String? = null,
            @SerializedName("rate_float") val rate: Float? = null
    )
}
