package com.example.discount.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WelcomeViewModel : ViewModel()
{

    val time: MutableState<String> = mutableStateOf("")

    fun getCurrentDateTime() {
        viewModelScope.launch (Dispatchers.IO){
            while (true) {
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm", Locale.getDefault())
                val currentDate = sdf.format(Date())
                time.value = currentDate
                delay(60000)
            }
        }
    }

}