package com.example.gpt.ui.Screens.MainScreen

sealed class MainIntent {
    data class LoadMessages(val message: String) : MainIntent()
    data class OnMessageChange(val message: String) : MainIntent()
    object onClear : MainIntent()
}
