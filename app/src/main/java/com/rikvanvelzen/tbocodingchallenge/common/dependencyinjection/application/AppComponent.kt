package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.application

import com.rikvanvelzen.tbocodingchallenge.TBOCodingChallengeApplication
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.module.ActivityBindingModule
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.module.FragmentBindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class,
    ApplicationModule::class,
//    AndroidInjectionModule::class,
    RepositoryModule::class,
    NetworkModule::class,
//    ApplicationModule::class,
//    ViewModelFactoryModule::class,
    UseCasesModule::class
//    ViewModelModule::class
])
interface AppComponent : AndroidInjector<TBOCodingChallengeApplication>