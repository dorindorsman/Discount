package com.example.discount.welcome

sealed class WelcomeEvent {
    data object ContinueClicked : WelcomeEvent()
    data object WebItemClicked : WelcomeEvent()

}