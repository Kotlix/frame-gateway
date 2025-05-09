package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayBasicRegisterRequest(
    val login: String,
    val password: String,
    val username: String,
    val email: String,
)
