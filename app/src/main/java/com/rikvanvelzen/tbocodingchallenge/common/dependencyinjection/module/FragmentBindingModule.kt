package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.module

import com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates.ExchangeRatesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

  @ContributesAndroidInjector
  abstract fun contributeExchangeRatesFragment(): ExchangeRatesFragment
}
