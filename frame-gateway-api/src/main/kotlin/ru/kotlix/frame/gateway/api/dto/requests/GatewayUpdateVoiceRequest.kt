package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayUpdateVoiceRequest(
    val name: String,
    val directoryId: Long,
    val order: Int,
)
