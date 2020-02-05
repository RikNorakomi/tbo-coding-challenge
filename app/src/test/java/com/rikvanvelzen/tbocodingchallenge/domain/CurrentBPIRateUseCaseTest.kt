package com.rikvanvelzen.tbocodingchallenge.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rikvanvelzen.tbocodingchallenge.common.mock
import com.rikvanvelzen.tbocodingchallenge.common.whenever
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinCurrentExchangeRate
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinPriceIndexDTO
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinPriceIndexDTO.Currency
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinPriceIndexDTO.Time
import com.rikvanvelzen.tbocodingchallenge.data.repositories.BPIRatesRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CurrentBPIRateUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val EUR = "EUR"

    private val lastUpdated1 = "lastUpdate1"
    private val lastUpdated2 = "lastUpdate2"
    private val rateFormatted1 = "rate1"
    private val rateFormatted2 = "rate2"
    private val currencyRateMap1 = mapOf(EUR to Currency(null, null, null, rateFormatted1))
    private val currencyRateMap2 = mapOf(EUR to Currency(null, null, null, rateFormatted2))

    private val bpiRatesRepository = mock<BPIRatesRepository>()

    lateinit var SUT: CurrentBPIRateUseCaseImpl

    @Before
    fun setup() {
        SUT = CurrentBPIRateUseCaseImpl(bpiRatesRepository)
    }

    @Test
    fun getCurrentBitcoinRate_repoEmitsTwoItems_correctValuesReceived() {
        // arrange
        val item1 = BitcoinPriceIndexDTO(Time(lastUpdated1), null, null, currencyRateMap1)
        val item2 = BitcoinPriceIndexDTO(Time(lastUpdated2), null, null, currencyRateMap2)

        // act
        whenever(bpiRatesRepository.getCurrentRate())
                .thenReturn(Observable.just(item1, item2))

        // assert
        SUT.getCurrentBitcoinRate(EUR)
                .test()
                .assertValues(BitcoinCurrentExchangeRate(rateFormatted1, lastUpdated1, EUR),
                        BitcoinCurrentExchangeRate(rateFormatted2, lastUpdated2, EUR))
                .dispose()

    }

    @Test
    fun getCurrentBitcoinRate_error_observableCompletes() {
        // arrange
        val errorThrowable = Throwable("Error response")

        // act
        whenever(bpiRatesRepository.getCurrentRate())
                .thenReturn(Observable.error(errorThrowable))

        // assert
        SUT.getCurrentBitcoinRate(EUR)
                .test()
                .assertError(errorThrowable)
                .dispose()

    }
}