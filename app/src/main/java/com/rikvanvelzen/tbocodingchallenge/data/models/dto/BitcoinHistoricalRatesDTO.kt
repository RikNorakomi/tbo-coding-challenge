package com.rikvanvelzen.tbocodingchallenge.data.models.dto

import com.google.gson.annotations.SerializedName

/**
 * Update times are formatted as:
 *  "updated":      "Feb 3, 2020 15:28:48 UTC",
 *  "updatedISO":   "2020-02-03T15:28:48+00:00"
 *
 *  BitcoinPriceIndex [bpi] map has format:
 *  "2020-02-01": 9388.6617
 */
data class BitcoinHistoricalRatesDTO(

        val disclaimer: String? = null,
        val time: Time? = null,
        val bpi: Map<String, Float>? = null
) {
    data class Time(

            @SerializedName("updated") val updatedUTC: String? = null,
            val updatedISO: String? = null
    )
}
