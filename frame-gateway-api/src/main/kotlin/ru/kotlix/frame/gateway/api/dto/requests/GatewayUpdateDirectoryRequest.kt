package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayUpdateDirectoryRequest(
    val name: String,
    val directoryId: Long?,
    val order: Int,
)
