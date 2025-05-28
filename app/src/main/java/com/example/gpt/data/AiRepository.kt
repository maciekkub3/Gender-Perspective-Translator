package com.example.gpt.data

import com.example.gpt.data.network.models.ChatRequest
import com.example.gpt.data.network.models.ChatResponse
import retrofit2.Response
import javax.inject.Inject

class AiRepository @Inject constructor(
    private val aiService: AiService
) {

    suspend fun getResponse(request: ChatRequest): ChatResponse {
        return aiService.getResponse(request)
    }

}