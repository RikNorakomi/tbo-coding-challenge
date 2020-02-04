package com.rikvanvelzen.tbocodingchallenge.ui

import android.os.Bundle
import com.rikvanvelzen.tbocodingchallenge.R
import com.rikvanvelzen.tbocodingchallenge.ui.screens.base.BaseActivity
import com.rikvanvelzen.tbocodingchallenge.ui.screens.exchangerates.ExchangeRatesFragment

class MainActivity : BaseActivity() {

    private val fragment by lazy { ExchangeRatesFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, null)
                    .commit()
        }
    }
}
