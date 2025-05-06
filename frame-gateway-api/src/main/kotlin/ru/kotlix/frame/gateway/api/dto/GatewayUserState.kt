package ru.kotlix.frame.gateway.api.dto

import java.time.OffsetDateTime

data class GatewayUserState(
    val userId: Long,
    val online: Boolean,
    val lastActive: OffsetDateTime,
)
