package com.rikvanvelzen.tbocodingchallenge.data.api

import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinHistoricalRatesDTO
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinPriceIndexDTO
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 *
 * For more info on the CoinDesk api see:
 * https://www.coindesk.com/coindesk-api
 */
interface BitcoinPriceIndexApi {

    @GET("currentprice.json")
    fun getCurrentPrice(): Observable<BitcoinPriceIndexDTO>

    // url end part looks like: historical/close.json?start=2013-09-01&end=2013-09-05
    @GET("historical/close.json")
    fun getHistoricalPrices(@Query("start") startDate: String,
                            @Query("end") endDate: String): Single<BitcoinHistoricalRatesDTO>
}