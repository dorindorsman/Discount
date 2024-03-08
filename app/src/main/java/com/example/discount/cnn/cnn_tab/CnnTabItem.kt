package com.example.discount.cnn.cnn_tab

import com.example.discount.R
import com.example.discount.cnn.Source

sealed class CnnTabItem(var title: Int, var type: Source, var id: String) {
    data object Received : CnnTabItem(R.string.tab_travel, Source.TRAVEL, "tab_travel")
    data object Send : CnnTabItem(R.string.tab_sport_entertainment, Source.SPORT_ENTERTAINMENT, "tab_sport_entertainment")
}


