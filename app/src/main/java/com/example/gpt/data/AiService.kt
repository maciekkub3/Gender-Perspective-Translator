package com.example.gpt.data

import com.example.gpt.data.network.models.ChatRequest
import com.example.gpt.data.network.models.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AiService {

    @POST("/v1/responses")
    suspend fun sendChatPrompt(@Body request: ChatRequest): ChatResponse
}
