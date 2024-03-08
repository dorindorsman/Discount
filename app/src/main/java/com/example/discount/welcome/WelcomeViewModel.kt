package com.example.discount.welcome

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discount.cnn.CnnViewModel
import com.example.discount.repository.LinkRepository
import com.example.discount.website.WebViewLegalLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WelcomeViewModel(
    private val navigateToCnnView: () -> Unit,
    private val webViewLegalLauncher: WebViewLegalLauncher,
    val linkRepository : LinkRepository,
    ) : ViewModel()
{

    companion object {
        const val TAG = "WelcomeViewModel"
    }

    var time by mutableStateOf("")

    init {
        startTimer()
    }

    fun handle(event: WelcomeEvent) {
        Log.d(TAG, "event: $event")

        when (event) {
            WelcomeEvent.ContinueClicked -> navigateToCnnView()
            is WelcomeEvent.WebItemClicked -> navigateToWeb(linkRepository.getLink())
        }
    }

    private fun startTimer() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm", Locale.getDefault())
        time = sdf.format(Date())

        viewModelScope.launch (Dispatchers.IO){
            while (true) {
                delay(1000)
                time = sdf.format(Date())
            }
        }
    }

    private fun navigateToWeb(link: String?) {
        if(link.isNullOrBlank()){
            return
        }
        Log.d(CnnViewModel.TAG, "navigateToWeb $link")
        linkRepository.setLink(link)
        webViewLegalLauncher.startEulaActivity(url = link)
    }


}