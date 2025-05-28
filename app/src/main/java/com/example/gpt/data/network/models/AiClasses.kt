package com.example.gpt.data.network.models

data class ChatRequest(
    val model: String,
    val input: String
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResponse(
    val output: List<ResponseMessage>
)

data class ResponseMessage(
    val role: String,
    val status: String,
    val content: List<MessageContent>
)

data class MessageContent(
    val type: String,
    val text: String
)

data class ApiKey(val value: String)