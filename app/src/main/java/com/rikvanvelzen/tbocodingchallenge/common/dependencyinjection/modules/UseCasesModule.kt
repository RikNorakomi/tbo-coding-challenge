package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.modules

import com.rikvanvelzen.tbocodingchallenge.data.repositories.BPIRatesRepository
import com.rikvanvelzen.tbocodingchallenge.domain.CurrentBPIRateInteractor
import com.rikvanvelzen.tbocodingchallenge.domain.CurrentBPIRateUseCase
import com.rikvanvelzen.tbocodingchallenge.domain.HistoricalBPIRatesInteractor
import com.rikvanvelzen.tbocodingchallenge.domain.HistoricalBPIRatesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun providesHistoricalBPIRatesUseCase(bpiRatesRepository: BPIRatesRepository): HistoricalBPIRatesUseCase = HistoricalBPIRatesInteractor(bpiRatesRepository)

    @Provides
    fun providesBitcoinCurrentExchangeRateUseCase(bpiRatesRepository: BPIRatesRepository): CurrentBPIRateUseCase = CurrentBPIRateInteractor(bpiRatesRepository)
}