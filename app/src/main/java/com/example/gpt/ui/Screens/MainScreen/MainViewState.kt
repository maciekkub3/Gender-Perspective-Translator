package com.example.gpt.ui.Screens.MainScreen

data class MainViewState(
    val loading: Boolean = false,
    val error: String? = null,
    var message: String = "",
    val response: String = ""
)
