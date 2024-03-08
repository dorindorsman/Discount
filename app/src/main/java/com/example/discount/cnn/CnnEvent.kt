package com.example.discount.cnn

sealed class CnnEvent {
       data class WebItemClicked(val link: String) : CnnEvent()
}
