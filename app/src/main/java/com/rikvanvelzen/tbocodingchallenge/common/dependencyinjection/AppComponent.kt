package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection

import com.rikvanvelzen.tbocodingchallenge.TBOCodingChallengeApplication
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ActivityBindingModule::class,
            FragmentBindingModule::class,
            RepositoryModule::class,
            NetworkModule::class,
            ApplicationModule::class,
            UseCasesModule::class,
            ViewModelModule::class
        ]
)
interface AppComponent : AndroidInjector<TBOCodingChallengeApplication>

