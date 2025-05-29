package com.example.gpt.ui.Screens.MainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gpt.data.AiRepository
import com.example.gpt.data.network.models.ChatRequest
import com.example.gpt.data.network.models.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val aiRepository: AiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState> = _state

    fun handleIntent(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.LoadMessages -> loadMessages(intent.message)
                is MainIntent.OnMessageChange -> {
                    _state.value = _state.value.copy(message = intent.message)
                }
                is MainIntent.onClear -> {
                    _state.value = MainViewState()
                }

            }
        }
    }

    private suspend fun loadMessages(message: String) {
        _state.value = _state.value.copy(loading = true)
        try {
            val response = aiRepository.getResponse(
                ChatRequest(
                    model = "gpt-4.1-mini",
                    input = listOf(
                        Message(
                            role = "developer",
                            content = "If the user input is empty or blank, do not respond at all. Act as a humorous relationship phrase translator. When given a sentence, respond only with an exaggerated interpretation based on common relationship stereotypes from popular culture. Do not include the original sentence, labels,translation word or quotes. Keep it lighthearted and clearly for fun. do not include `translation` or `quote` in your response.."
                        ),
                        Message(
                            role = "user",
                            content = message
                        )
                    )
                )
            )
            _state.value = _state.value.copy(
                loading = false,
                response = response.output.firstOrNull()?.content?.firstOrNull()?.text
                    ?: "No response",
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(loading = false, error = e.message ?: "Unknown error")
        }
    }
}
