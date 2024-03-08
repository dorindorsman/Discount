package com.example.discount.ui.theme

import android.provider.CalendarContract
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFF4FF46B)
val primaryLightColor = Color(0xFF8CFF9C)
val primaryDarkColor = Color(0xFF006D21)
val primaryTextColor = Color(0xFF000000)
val secondaryColor = Color(0xFF8E24AA)
val secondaryLightColor = Color(0xFFC158DC)
val secondaryDarkColor = Color(0xFF5C007A)
val secondaryTextColor = Color(0xFFFFFFFF)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)
val CnnBlueColor = Color(0xFF0080FF)
val ScrollBarColor = Color(0xFF3A3E44)


val CalendarContract.Colors.CnnBlueColor: Color
    @Composable
    get() = CnnBlueColor

val CalendarContract.Colors.ScrollBarColor: Color
    @Composable
    get() = ScrollBarColor