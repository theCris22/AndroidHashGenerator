package com.crisnavarro.androidhashgenerator.core

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.shortSnackBar(message: String, action: () -> Unit = {}) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).setAction("OK") { action() }.show()
}