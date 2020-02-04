package com.rikvanvelzen.tbocodingchallenge.ui.di

import com.rikvanvelzen.tbocodingchallenge.ui.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(ExchangeRatesFragmentModule::class))
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}