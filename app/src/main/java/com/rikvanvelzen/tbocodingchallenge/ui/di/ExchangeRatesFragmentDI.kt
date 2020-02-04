package com.rikvanvelzen.tbocodingchallenge.ui.di

import androidx.fragment.app.Fragment
import com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates.ExchangeRatesFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Subcomponent/*(modules = ...)*/
interface ExchangeRatesFragmentSubcomponent : AndroidInjector<ExchangeRatesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ExchangeRatesFragment>()
}

@Module(subcomponents = [ExchangeRatesFragmentSubcomponent::class])
abstract class ExchangeRatesFragmentModule {

    @Binds
    @IntoMap
//    @FragmentKey(ExchangeRatesFragment::class)
    abstract fun bindExchangeRatesFragmentInjectorFactory(builder: ExchangeRatesFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}