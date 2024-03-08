package com.example.discount.cnn

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.discount.remote.OkHttpWebService
import com.example.discount.repository.LinkRepository
import com.example.discount.website.WebViewLegalLauncher

class CnnViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return CnnViewModel(
            okHttpWebService = OkHttpWebService(),
            linkRepository = LinkRepository,
            webViewLegalLauncher = WebViewLegalLauncher(appContext)
        ) as T
    }
}





