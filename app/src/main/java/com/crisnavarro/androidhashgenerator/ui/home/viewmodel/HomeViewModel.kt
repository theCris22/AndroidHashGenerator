package com.crisnavarro.androidhashgenerator.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import java.security.MessageDigest

class HomeViewModel : ViewModel() {

    fun makeHash(algorithm: String, text: String) =
        toHexadecimal(MessageDigest.getInstance(algorithm).digest(text.toByteArray()))


    private fun toHexadecimal(byteArray: ByteArray) = byteArray.joinToString("") {
        "%02x".format(it)
    }

}