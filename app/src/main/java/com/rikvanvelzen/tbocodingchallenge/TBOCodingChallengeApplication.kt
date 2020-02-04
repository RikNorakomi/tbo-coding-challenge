package com.rikvanvelzen.tbocodingchallenge

import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TBOCodingChallengeApplication : Application(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

       // TODO dagger impl
//        applicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(ApplicationModule(this))
//                .build()
        DaggerAppComponent.create().inject(this)
                .inject(this)
    }
}

//class PurrfectApp : Application(), HasAndroidInjector {
//
//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
//
//    override fun androidInjector(): AndroidInjector<Any> {
//        return dispatchingAndroidInjector
//    }
//
//    override fun onCreate() {
//        DaggerAppComponent.create().inject(this)
//        super.onCreate()
//    }
//}