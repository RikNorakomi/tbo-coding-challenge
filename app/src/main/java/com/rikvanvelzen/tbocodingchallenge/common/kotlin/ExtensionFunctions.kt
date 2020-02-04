/*
 * Created by Rik van Velzen, Norakomi Software Development.
 * Copyright (c) 2020. All rights reserved
 * Last modified 1/25/20 1:58 PM
 */

package com.rikvanvelzen.tbocodingchallenge.common.kotlin

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { postValue(initialValue) }

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}

fun CharSequence.toFloat(): Float? {
    return try {
        this.toString().toFloat()
    } catch (e: Exception) {
        null
    }
}

fun Context.showToast(msg: String) {

    val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)

    val v = toast.view.findViewById<View>(android.R.id.message) as TextView
    v.gravity = Gravity.CENTER

    toast.show()
}
