package com.rikvanvelzen.tbocodingchallenge.ui.di

import android.app.Activity
import com.rikvanvelzen.tbocodingchallenge.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(MainActivitySubcomponent::class))
abstract class MainActivityModule {
    @Binds
    @IntoMap
//    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}