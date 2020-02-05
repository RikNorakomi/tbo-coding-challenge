package com.rikvanvelzen.tbocodingchallenge.domain

import com.rikvanvelzen.tbocodingchallenge.common.mock
import com.rikvanvelzen.tbocodingchallenge.common.whenever
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import com.rikvanvelzen.tbocodingchallenge.data.models.dto.BitcoinHistoricalRatesDTO
import com.rikvanvelzen.tbocodingchallenge.data.repositories.BPIRatesRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HistoricalBPIRatesUseCaseTest {

    private val date1 = "2020-02-01"
    private val rate1 = 9388.6617F
    private val date2 = "2020-02-02"
    private val rate2 = 9311.6617F

    private val bpiRatesRepository = mock<BPIRatesRepository>()
    lateinit var SUT: HistoricalBPIRatesImpl

    @Before
    fun setup() {
        SUT = HistoricalBPIRatesImpl(bpiRatesRepository)
    }

    @Test
    fun getBitcoinRates_error_errorResponseReturned() {
        // arrange
        val errorResponse = Throwable("Error response")

        // act
        whenever(bpiRatesRepository.getHistoricalRates(anyString(), anyString()))
                .thenReturn(Single.error(errorResponse))

        // assert
        SUT.getBitcoinRates(1)
                .test()
                .assertError(errorResponse)
    }

    @Test
    fun getBitcoinRates_success_observableCompletes() {
        // arrange
        val resultObject = BitcoinHistoricalRatesDTO(null, null, BitcoinHistoricalRatesDTO.Time())

        // act
        whenever(bpiRatesRepository.getHistoricalRates(anyString(), anyString()))
                .thenReturn(Single.just(resultObject))

        // assert
        SUT.getBitcoinRates(0).test().assertComplete()
        SUT.getBitcoinRates(1).test().assertComplete()
    }

    @Test
    fun getBitcoinRates_success_observable() {
        // arrange
        val bpiMap = mapOf(date1 to rate1, date2 to rate2)
        val resultObject = BitcoinHistoricalRatesDTO(null, bpiMap, null)

        // act
        whenever(bpiRatesRepository.getHistoricalRates(anyString(), anyString()))
                .thenReturn(Single.just(resultObject))

        // assert
        val expectedResult = mutableListOf(
                BitcoinHistoricalExchangeRate(rate1, date1, DEFAULT_CURRENCY),
                BitcoinHistoricalExchangeRate(rate2, date2, DEFAULT_CURRENCY))

        SUT.getBitcoinRates(0)
                .test()
                .assertValue(expectedResult)
    }
}