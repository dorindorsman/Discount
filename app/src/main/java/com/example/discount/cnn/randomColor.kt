package com.example.discount.cnn

import androidx.compose.ui.graphics.Color

fun generateRandomColor(): Color {
    val red = (0..255).random()
    val green = (0..255).random()
    val blue = (0..255).random()
    return Color(red, green, blue)
}