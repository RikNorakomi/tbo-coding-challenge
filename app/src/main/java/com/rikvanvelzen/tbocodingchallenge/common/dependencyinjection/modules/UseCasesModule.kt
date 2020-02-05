package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.modules

import com.rikvanvelzen.tbocodingchallenge.data.repositories.BPIRatesRepository
import com.rikvanvelzen.tbocodingchallenge.domain.CurrentBPIRateUseCaseImpl
import com.rikvanvelzen.tbocodingchallenge.domain.CurrentBPIRateUseCase
import com.rikvanvelzen.tbocodingchallenge.domain.HistoricalBPIRatesImpl
import com.rikvanvelzen.tbocodingchallenge.domain.HistoricalBPIRatesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun providesHistoricalBPIRatesUseCase(bpiRatesRepository: BPIRatesRepository): HistoricalBPIRatesUseCase = HistoricalBPIRatesImpl(bpiRatesRepository)

    @Provides
    fun providesBitcoinCurrentExchangeRateUseCase(bpiRatesRepository: BPIRatesRepository): CurrentBPIRateUseCase = CurrentBPIRateUseCaseImpl(bpiRatesRepository)
}