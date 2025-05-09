package ru.kotlix.frame.gateway.api.dto.entities

data class GatewayFullProfileInfoDto(
    val id: Long,
    val login: String,
    val username: String,
    val email: String,
)
