package com.example.discount.repository

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

object LinkRepository {

    private const val TAG = "LinkRepository"

    private var link = mutableStateOf("")

    fun setLink(url: String) {
        Log.d(TAG, url)
        link.value = url
    }

    fun getLink() = link.value

    fun openLink(){

    }
}