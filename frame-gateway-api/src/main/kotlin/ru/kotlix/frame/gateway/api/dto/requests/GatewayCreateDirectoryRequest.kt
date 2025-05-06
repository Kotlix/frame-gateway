package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayCreateDirectoryRequest(
    val name: String,
    val directoryId: Long?,
    val order: Int,
)
