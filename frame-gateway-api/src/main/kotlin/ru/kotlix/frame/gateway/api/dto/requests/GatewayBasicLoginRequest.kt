package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayBasicLoginRequest(
    val login: String,
    val password: String,
)
