package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.module

import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.scope.ActivityScope
import com.rikvanvelzen.tbocodingchallenge.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
