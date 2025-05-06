package ru.kotlix.frame.gateway.api.dto.entities

import java.time.OffsetDateTime

class GatewayInviteTokenDto(
    val token: String,
    val expiresAt: OffsetDateTime,
    val isOneTime: Boolean,
)
