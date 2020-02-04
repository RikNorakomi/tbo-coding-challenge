package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.application

import com.rikvanvelzen.tbocodingchallenge.TBOCodingChallengeApplication
import com.rikvanvelzen.tbocodingchallenge.ui.di.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidInjectionModule::class,
    RepositoryModule::class,
    ApplicationModule::class,
    ViewModelFactoryModule::class,
    UseCasesModule::class,
    ViewModelModule::class,
    MainActivityModule::class])
interface AppComponent : AndroidInjector<TBOCodingChallengeApplication>