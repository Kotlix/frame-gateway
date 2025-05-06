package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayCreateChatRequest(
    val name: String,
    val directoryId: Long,
    val order: Int,
)
