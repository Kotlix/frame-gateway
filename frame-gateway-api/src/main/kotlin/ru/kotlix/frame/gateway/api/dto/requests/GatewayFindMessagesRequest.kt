package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayFindMessagesRequest(
    val name: String,
    val pageOffset: Long,
    val pageCount: Long,
)
