package com.rikvanvelzen.tbocodingchallenge.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rikvanvelzen.tbocodingchallenge.R
import com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates.ExchangeRateViewModel
import com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates.ExchangeRatesFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
//class MainActivity : AppCompatActivity(), HasFragmentInjector {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    @Inject
//    lateinit var fragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    val fragment by lazy { ExchangeRatesFragment() }

//    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? {
//        return fragmentInjector
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this, viewModelFactory)[ExchangeRateViewModel::class.java]

        Log.e(TAG, "MainVM = $mainViewModel")

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, null)
                    .commit()
        }
    }
}

// {
//
//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
//
//    override fun androidInjector(): AndroidInjector<Any> {
//        return dispatchingAndroidInjector
//    }
//
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val mainViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]