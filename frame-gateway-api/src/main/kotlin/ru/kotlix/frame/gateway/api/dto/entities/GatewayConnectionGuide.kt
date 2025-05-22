package ru.kotlix.frame.gateway.api.dto.entities

data class GatewayConnectionGuide(
    val hostAddress: String,
    val secret: String,
    val channelId: Long,
    val shadowId: Int,
)
