package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayUpdateChatRequest(
    val name: String,
    val directoryId: Long,
    val order: Int,
)
