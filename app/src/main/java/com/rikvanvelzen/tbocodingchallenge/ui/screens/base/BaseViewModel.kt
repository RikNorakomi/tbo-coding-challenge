/*
 * Created by Rik van Velzen, Norakomi Software Development.
 * Copyright (c) 2020. All rights reserved
 * Last modified 1/23/20 12:24 PM
 */

package com.rikvanvelzen.tbocodingchallenge.ui.screens.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rikvanvelzen.tbocodingchallenge.common.SingleLiveEvent
import com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.modules.SCHEDULER_IO
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

open class BaseViewModel : ViewModel() {

    val TAG = javaClass.simpleName

    val isLoading = MutableLiveData<Boolean>()
    val disposables: CompositeDisposable = CompositeDisposable()

    private val navigateBack = SingleLiveEvent<Any>()

    /**************************************************
     * Lifecycle functions
     **************************************************/

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    /**************************************************
     * Public functions
     **************************************************/

    fun onBackButtonClicked() {
        navigateBack.call()
    }

    fun shouldNavigateBack(): LiveData<Any> {
        return navigateBack
    }
}
