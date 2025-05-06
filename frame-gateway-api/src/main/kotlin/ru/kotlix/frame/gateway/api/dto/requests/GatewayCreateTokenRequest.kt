package ru.kotlix.frame.gateway.api.dto.requests

import java.time.OffsetDateTime

data class GatewayCreateTokenRequest(
    val expiresAt: OffsetDateTime?,
    val isOneTime: Boolean,
)
