package ru.kotlix.frame.gateway.api.dto.entities

data class GatewayConnectionGuide(
    val hostAddress: String,
    val channelId: Long,
    val shadowId: Int,
)
