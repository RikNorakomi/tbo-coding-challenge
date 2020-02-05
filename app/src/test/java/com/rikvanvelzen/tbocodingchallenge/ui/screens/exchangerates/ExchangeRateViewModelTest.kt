package com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rikvanvelzen.tbocodingchallenge.common.getOrAwaitValue
import com.rikvanvelzen.tbocodingchallenge.common.mock
import com.rikvanvelzen.tbocodingchallenge.common.whenever
import com.rikvanvelzen.tbocodingchallenge.data.models.domain.BitcoinHistoricalExchangeRate
import com.rikvanvelzen.tbocodingchallenge.domain.CurrentBPIRateUseCase
import com.rikvanvelzen.tbocodingchallenge.domain.DEFAULT_CURRENCY
import com.rikvanvelzen.tbocodingchallenge.domain.HistoricalBPIRatesUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.reset
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExchangeRateViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val historicalBPIRatesUseCase = mock<HistoricalBPIRatesUseCase>()
    private val currentBPIRateUseCase = mock<CurrentBPIRateUseCase>()

    lateinit var SUT: ExchangeRateViewModel

    @Before
    fun initTest() {
        reset(historicalBPIRatesUseCase)
    }

    @Before
    fun setup() {
        SUT = ExchangeRateViewModel(historicalBPIRatesUseCase,
                currentBPIRateUseCase,
                // Trampoline scheduler is great for testing.
                // It executes all tasks in a FIFO manner on one of the participating threads.
                Schedulers.trampoline(),
                Schedulers.trampoline())
    }

    @Test
    fun getExchangeRates_historicalRatesSuccessfulLoaded_LiveDataUpdated() {

        // Arrange / Given
        val expectedResult = mutableListOf(
                BitcoinHistoricalExchangeRate(1f, "date1", DEFAULT_CURRENCY),
                BitcoinHistoricalExchangeRate(2f, "date2", DEFAULT_CURRENCY))

        // Act / When
        whenever(historicalBPIRatesUseCase.getBitcoinRates(anyInt()))
                .thenReturn(Single.just(expectedResult))
        SUT.getHistoricalRates()
        expectedResult.reverse()

        // Assert
        assertEquals(SUT.historicalExchangeRates.getOrAwaitValue(), expectedResult)
    }

    @Test
    fun getExchangeRates_getHistoricalRatesReturnError_errorLiveDataUpdated() {

        // Arrange / Given
        val errorResult = Throwable("error throwable")

        // Act / When
        whenever(historicalBPIRatesUseCase.getBitcoinRates(anyInt()))
                .thenReturn(Single.error(errorResult))
        SUT.getHistoricalRates()

        // Assert
        assertEquals(SUT.error.getOrAwaitValue(), errorResult)
    }
}