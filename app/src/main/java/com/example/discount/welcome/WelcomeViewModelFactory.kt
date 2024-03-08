package com.example.discount.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.discount.repository.LinkRepository
import com.example.discount.website.WebViewLegalLauncher

class WelcomeViewModelFactory(private val navigateToCnnView: () -> Unit, private val appContext: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return WelcomeViewModel(
            navigateToCnnView = navigateToCnnView,
            webViewLegalLauncher =  WebViewLegalLauncher(appContext),
            linkRepository = LinkRepository
        ) as T
    }
}





