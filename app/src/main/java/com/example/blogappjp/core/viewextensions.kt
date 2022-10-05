package com.example.blogappjp.core

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hideKeyboard() {
    val inm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inm.hideSoftInputFromWindow(windowToken, 0)
}