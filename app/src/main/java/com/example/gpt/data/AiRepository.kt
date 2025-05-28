package com.example.gpt.data

import com.example.gpt.data.network.models.ChatRequest
import com.example.gpt.data.network.models.ChatResponse
import javax.inject.Inject

class AiRepository @Inject constructor(
    private val aiService: AiService
) {

    suspend fun sendChatPrompt(request: ChatRequest): ChatResponse {
        return aiService.sendChatPrompt(request)
    }

}
